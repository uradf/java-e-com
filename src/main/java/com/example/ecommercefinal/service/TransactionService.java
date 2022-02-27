package com.example.ecommercefinal.service;

import com.example.ecommercefinal.config.JwtTokenUtil;
import com.example.ecommercefinal.entity.PickedProduct;
import com.example.ecommercefinal.entity.TransactionDetail;
import com.example.ecommercefinal.entity.TransactionHead;
import com.example.ecommercefinal.exception.CustomerBalanceRunOutException;
import com.example.ecommercefinal.exception.ProductOutOfStockException;
import com.example.ecommercefinal.repository.TransactionDetailRepository;
import com.example.ecommercefinal.repository.TransactionHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TransactionService {
    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    @Autowired
    TransactionHeadRepository transactionHeadRepository;

    @Autowired
    BasketProductService basketProductService;

    @Autowired
    ProductService productService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    WalletGateWay walletGateWay;

    public List<TransactionHead> get(String token) {
        List<TransactionHead> transactionHeadList = new ArrayList<TransactionHead>();
        if (!jwtTokenUtil.validate(token)) return transactionHeadList;
        int customer = jwtTokenUtil.getUserId(token);
        Optional<TransactionHead> result = transactionHeadRepository.findById(customer);
        if (result.isPresent()) transactionHeadList.add(result.get());
        return transactionHeadList;
    }


    @Transactional
    public List<TransactionHead> createTransaction(String token) {
        List<TransactionHead> transactionHeadList = new ArrayList<TransactionHead>();
        if (!jwtTokenUtil.validate(token)) return transactionHeadList;
        int customer = jwtTokenUtil.getUserId(token);

        List<PickedProduct> validatePickedProducts = basketProductService.get(token);
        AtomicInteger userBalance = new AtomicInteger(walletGateWay.getBalance("MOCK_ADDRESS", "MOCK_SIGNATURE"));
        validatePickedProducts.forEach(pickedProduct -> {
            boolean isSuccess = productService.decreaseStock(pickedProduct.getId());
            if (!isSuccess) throw new ProductOutOfStockException(pickedProduct.getName() + " are out of Stock");
            if (userBalance.get() - pickedProduct.getPrice() < 0) throw new CustomerBalanceRunOutException("Balance exceed = " + (userBalance.get() - pickedProduct.getPrice()));
            userBalance.addAndGet(-pickedProduct.getPrice());
        });

        TransactionHead transactionHead = new TransactionHead();
        Instant instant = Instant.now();
        String currentDate = instant.atZone(ZoneId.of("UTC")).toLocalDateTime().toString();

		transactionHead.setCustomer(customer);
		transactionHead.setCreateat(currentDate);

        TransactionHead resultHead = transactionHeadRepository.save(transactionHead);
        int head = resultHead.getId();
        List<PickedProduct> pickedProducts = basketProductService.get(token);
        List<TransactionDetail> transactionDetailList = new ArrayList<TransactionDetail>();
        pickedProducts.forEach(pickedProduct -> {
            TransactionDetail transactionDetail = new TransactionDetail();
            transactionDetail.setProduct(pickedProduct.getId());
            transactionDetail.setName(pickedProduct.getName());
            transactionDetail.setPrice(pickedProduct.getPrice());
            transactionDetail.setAmount(1);
            transactionDetail.setHead(head);
            transactionDetailList.add(transactionDetail);
        });
        List<TransactionDetail> transactionDetailList1 = transactionDetailRepository.saveAll(transactionDetailList);
        Optional<TransactionHead> result = transactionHeadRepository.findById(customer);

        if (result.isPresent()) {
            result.get().setTransactionDetails(transactionDetailList1);
            transactionHeadList.add(result.get());
        }
        return transactionHeadList;
    }

    public void setTransactionDetailRepository(TransactionDetailRepository transactionDetailRepository) {
        this.transactionDetailRepository = transactionDetailRepository;
    }

    public void setTransactionHeadRepository(TransactionHeadRepository transactionHeadRepository) {
        this.transactionHeadRepository = transactionHeadRepository;
    }

    public void setBasketProductService(BasketProductService basketProductService) {
        this.basketProductService = basketProductService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public void setWalletGateWay(WalletGateWay walletGateWay) {
        this.walletGateWay = walletGateWay;
    }
}

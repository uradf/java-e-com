package com.example.ecommercefinal.service;

import com.example.ecommercefinal.config.JwtTokenUtil;
import com.example.ecommercefinal.entity.Customer;
import com.example.ecommercefinal.entity.CustomerAddress;
import com.example.ecommercefinal.exception.AuthenFailedException;
import com.example.ecommercefinal.exception.CustomerNotFoundException;
import com.example.ecommercefinal.repository.CustomerAddressRepository;
import com.example.ecommercefinal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    public String getToken(String userid, String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes(),0, password.length());
        String hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);
        if (hashedPass.length() < 32) {
            hashedPass = "0" + hashedPass;
        }
        Optional<Customer> customer = customerRepository.findByUseridAndPassword(userid, hashedPass);
        if (customer.isPresent()) {
            return jwtTokenUtil.generateAccessToken(customer.get());
        }
        throw new CustomerNotFoundException();
    }

    public String getAddress(String token) {
        if (!jwtTokenUtil.validate(token)) throw new AuthenFailedException("ERROR");
        Optional<CustomerAddress> customer = customerAddressRepository.findById(jwtTokenUtil.getUserId(token));
        if (customer.isPresent()) {
            return customer.get().getProvince();
        }
        return "EMPTY";

    }
}

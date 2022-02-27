package com.example.ecommercefinal.controller;

import com.example.ecommercefinal.entity.TokenInput;
import com.example.ecommercefinal.response.TransactionResponse;
import com.example.ecommercefinal.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/purchase")
    public TransactionResponse purchase(@RequestBody TokenInput tokenInput) {
        return new TransactionResponse("/purchase", transactionService.createTransaction(tokenInput.getToken()));
    }

    @GetMapping("/purchase")
    public TransactionResponse get(@RequestParam(defaultValue = "0") String token) {
        return new TransactionResponse("/purchase", transactionService.get(token));
    }
}

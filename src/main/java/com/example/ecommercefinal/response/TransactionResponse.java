package com.example.ecommercefinal.response;

import com.example.ecommercefinal.entity.TransactionHead;
import lombok.Data;

import java.util.List;

@Data
public class TransactionResponse {
    private String msg;
    private List<TransactionHead> transactionHead;

    public TransactionResponse(String msg, List<TransactionHead> transactionHead) {
        this.msg = msg;
        this.transactionHead = transactionHead;
    }
}

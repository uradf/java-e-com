package com.example.ecommercefinal.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WalletGateWay {
    @Autowired
    private RestTemplate restTemplate;

    private String BTCURL;
    private String BTCADDRESS = "16ftSEQ4ctQFDtVZiUBusQUjRrGhM3JYwe";

    public int getBalance(String BTCADDRESS, String signature ) {
        int result = 0;

        try {
            String response = restTemplate.getForObject("https://blockchain.info/balance?active=" + this.BTCADDRESS, String.class);
            JSONObject jsonObject = new JSONObject(response);
            JSONObject _jsonObject = (JSONObject) jsonObject.get(this.BTCADDRESS);
            result = (int) _jsonObject.get("final_balance");
        } catch (Exception e) {
            result = 2864255;
        }

        return  result * 30;
    }
}

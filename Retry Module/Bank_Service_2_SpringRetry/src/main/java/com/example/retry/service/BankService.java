package com.example.retry.service;

import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.Date;

@Service
public class BankService {
    RestTemplate restTemplate = new RestTemplate();
    String customerServiceUrl = "http://localhost:9192/customerSvc/getCustomerDetails1234";
    public int count = 1;

    @Retryable(retryFor = {HttpClientErrorException.class, ConnectException.class}, maxAttempts = 3, backoff = @Backoff(delay = 10000L))
    public ResponseEntity<String> postRequest() {
        ResponseEntity<String> resp = null;

//        try{
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        System.out.println("Count: " + count++ + " Time: " + new Date());

        resp = restTemplate.exchange(customerServiceUrl, HttpMethod.GET, entity, String.class);
//        } catch (Exception ex){
//            System.out.println(ex);
//        }


        return resp;
    }

}

package com.example.retry.controller;

import com.example.retry.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/bankSvc")
public class BankServiceController {

    @Autowired
    BankService bankService;

    // This value will be used in the application.yml file
    public static final String bankSvcCB = "BankServiceRetry";

    @GetMapping("/getBankCards")
    public ResponseEntity<String> getBankCards() {
        String cusSvcResp = "";

        ResponseEntity<String> resp = bankService.postRequest();
        cusSvcResp = resp.getBody();

        String bankSvcResp = "Bank_Service Response -> Customer_Service Response: " + cusSvcResp;

        return ResponseEntity.ok(bankSvcResp);
    }

    public ResponseEntity<String> getCustomerDetails(Exception ex){
        return ResponseEntity.ok("Retry fallback method Response");
    }



}

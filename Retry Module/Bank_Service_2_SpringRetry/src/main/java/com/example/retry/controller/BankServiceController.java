package com.example.retry.controller;

import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/bankSvc")
public class BankServiceController {


    RestTemplate restTemplate = new RestTemplate();

    String customerServiceUrl = "http://localhost:9192/customerSvc/getCustomerDetails1234";

    // This value will be used in the application.yml file
    public static final String bankSvcCB = "BankServiceRetry";

    public int count = 1;

    @GetMapping("/getBankCards")
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 10000L))
    public ResponseEntity<String> getBankCards() {
        String cusSvcResp = "";

//        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            System.out.println("Count: " + count++ + " Time: " + new Date());

            ResponseEntity<String> apiResp =
                    restTemplate.exchange(customerServiceUrl, HttpMethod.GET, entity, String.class);
            cusSvcResp = apiResp.getBody();
//        } catch (Exception ex){
//            System.out.println(ex);
//        }


        String bankSvcResp = "Bank_Service Response -> Customer_Service Response: " + cusSvcResp;

        return ResponseEntity.ok(bankSvcResp);
    }

    public ResponseEntity<String> getCustomerDetails(Exception ex){
        return ResponseEntity.ok("Retry fallback method Response");
    }

}

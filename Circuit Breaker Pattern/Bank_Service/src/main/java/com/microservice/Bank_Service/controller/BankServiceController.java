package com.microservice.Bank_Service.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/bankSvc")
public class BankServiceController {

    RestTemplate restTemplate = new RestTemplate();

    String customerServiceUrl = "http://localhost:9192/customerSvc/getCustomerDetails";

    @GetMapping("/getBankCards")
    public ResponseEntity<String> getBankCards() {
        String cusSvcResp = "";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> apiResp =
                restTemplate.exchange(customerServiceUrl, HttpMethod.GET, entity, String.class);
        cusSvcResp = apiResp.getBody();

        String bankSvcResp = "Bank_Service Response -> Customer_Service Response: " + cusSvcResp;

        return ResponseEntity.ok(bankSvcResp);
    }

}

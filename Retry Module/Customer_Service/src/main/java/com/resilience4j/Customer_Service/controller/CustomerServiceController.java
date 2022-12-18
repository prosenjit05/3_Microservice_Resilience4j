package com.resilience4j.Customer_Service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerSvc")
public class CustomerServiceController {

    @GetMapping("/getCustomerDetails")
    public ResponseEntity<String> getCustomerDetails() {
        return ResponseEntity.ok("Customer Name: Mohan");
    }

}


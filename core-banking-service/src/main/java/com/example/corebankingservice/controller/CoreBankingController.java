/* Manages account-related functionalities, such as retrieving account details or transaction histories.*/

package com.example.corebankingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class CoreBankingController {

    @GetMapping
    public String getAccounts() {
        return "List of bank accounts";
    }
}
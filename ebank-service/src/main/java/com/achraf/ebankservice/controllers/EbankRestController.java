package com.achraf.ebankservice.controllers;

import com.achraf.ebankservice.entities.BankAccount;
import com.achraf.ebankservice.service.EbankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EbankRestController {
    private EbankService ebankService;

    public EbankRestController(EbankService ebankService) {
        this.ebankService = ebankService;
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts() {
        return ebankService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return ebankService.getAccountById(id);
    }

    @PostMapping("/accounts")
    public BankAccount saveCustomer(@RequestBody BankAccount bankAccount) {
        return ebankService.save(bankAccount);
    }
}

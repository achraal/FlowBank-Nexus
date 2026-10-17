package com.achraf.ebankservice.service;

import com.achraf.ebankservice.entities.BankAccount;
import com.achraf.ebankservice.feign.CustomerRestClient;
import com.achraf.ebankservice.model.Customer;
import com.achraf.ebankservice.repository.BankAccountRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    private BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository accountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }

    @McpTool(description = "Get all bank accounts")
    public List<BankAccount> getAllBankAccounts() {
        return accountRepository.findAll();
    }

    @McpTool(description = "Get a bank account by id")
    public BankAccount getAccountById(@McpToolParam(description = "The bank account id") String id) {
        BankAccount account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BankAccount not found"));
        account.setCustomer(
                customerRestClient.getCustomerById(account.getCustomerId()));
        return account;
    }

    @McpTool(description = "Save a new bank account")
    public BankAccount save(@McpToolParam(description = "The bank account to save (balance, type, customerId") BankAccount bankAccount) {
        try{
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return accountRepository.save(bankAccount);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

//    public BankAccount updateCustomer(BankAccount customer){
//        return customerRepository.save(customer);
//    }
//
//    public void deleteCustomer(Long id){
//        customerRepository.deleteById(id);
//    }
//
//    public void deleteAllCustomers(){
//        customerRepository.deleteAll();
//    }
}

package com.achraf.ebankservice.service;

import com.achraf.ebankservice.entities.BankAccount;
import com.achraf.ebankservice.feign.CustomerRestClient;
import com.achraf.ebankservice.model.Customer;
import com.achraf.ebankservice.repository.BankAccountRepository;
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

    public List<BankAccount> getAllBankAccounts() {
        return accountRepository.findAll();
    }

    public BankAccount getAccountById(String id) {
        BankAccount account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BankAccount not found"));
        account.setCustomer(
                customerRestClient.getCustomerById(account.getCustomerId()));
        return account;
    }

    public BankAccount save(BankAccount bankAccount) {
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

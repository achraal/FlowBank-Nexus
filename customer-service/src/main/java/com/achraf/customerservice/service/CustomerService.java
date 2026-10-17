package com.achraf.customerservice.service;

import com.achraf.customerservice.entities.Customer;
import com.achraf.customerservice.repository.CustomerRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(description = "Get all customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @McpTool(description = "Find a customer by id")
    public Customer getCustomerById(@McpToolParam(description = "The customer id") Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @McpTool(description = "Save a new customer")
    public Customer saveCustomer(@McpToolParam(description = "The Customer to save (name, email)") Customer customer){
        return customerRepository.save(customer);
    }

//    public Customer updateCustomer(Customer customer){
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

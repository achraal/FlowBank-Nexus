package com.achraf.customerservice.service;

import com.achraf.customerservice.entities.Customer;
import com.achraf.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer saveCustomer(Customer customer){
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

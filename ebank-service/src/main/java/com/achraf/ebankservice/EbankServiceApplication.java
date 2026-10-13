package com.achraf.ebankservice;

import com.achraf.ebankservice.entities.BankAccount;
import com.achraf.ebankservice.model.Customer;
import com.achraf.ebankservice.service.EbankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EbankService ebankService) {
        return args -> {
            for(int i = 1 ; i <= 3 ; i++){
                for(int j = 0 ; j < 5 ; j++){
                    ebankService.save(BankAccount.builder()
                            .type(Math.random() > 0.5 ? "CURRENT_ACCOUNT" : "SAVING_ACCOUNT")
                            .balance(10000 + Math.random() * 60000)
                            .customerId(i)
                            .build());
                }
            }
        };
    }
}

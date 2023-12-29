package com.example.bankbackendsecurityapp.controller;

import com.example.bankbackendsecurityapp.model.Customer;
import com.example.bankbackendsecurityapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer)
    {
        try {
            Customer savedCustomer = customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Customer successfully registered");
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("An exception occured :"+ex.getMessage());
        }
    }
}

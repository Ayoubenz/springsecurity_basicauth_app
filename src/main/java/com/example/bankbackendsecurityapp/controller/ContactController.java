package com.example.bankbackendsecurityapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public String saveContactInquityDetails()
    {
        return "Inquiry details are saved to the DB";
    }
}

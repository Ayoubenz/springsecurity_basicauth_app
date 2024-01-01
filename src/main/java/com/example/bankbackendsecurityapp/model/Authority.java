package com.example.bankbackendsecurityapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="authorities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}


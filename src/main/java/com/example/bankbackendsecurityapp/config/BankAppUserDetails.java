package com.example.bankbackendsecurityapp.config;

import com.example.bankbackendsecurityapp.model.Customer;
import com.example.bankbackendsecurityapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BankAppUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName = null;
        String password =null;
        List<GrantedAuthority> authorities = null;

        Customer customer = customerRepository.findByEmail(username);
        if(customer == null)
        {
            throw new UsernameNotFoundException("Username details not found: "+username);
        } else {
            userName = customer.getEmail();
            password = customer.getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customer.getRole()));
        }
        return new User(userName,password,authorities);
    }
}


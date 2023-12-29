package com.example.bankbackendsecurityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSercurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorize -> authorize
                        .anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();


        //Simple filters with basic auth

//        @Bean
//        SecurityFilterChain defaultSercurityFilterChain(HttpSecurity http) throws Exception{
//            http.authorizeHttpRequests(authorize -> authorize
//                            .requestMatchers("/myAccount","/myBalance","/myLoans","myCards")
//                            .authenticated()
//                            .requestMatchers("/notices","/contact").permitAll())
//                    .formLogin(Customizer.withDefaults())
//                    .httpBasic(Customizer.withDefaults());
//            return http.build();
    }
}

package com.example.bankbackendsecurityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {


    //Simple filters with basic auth

        @Bean
        SecurityFilterChain defaultSercurityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/myAccount", "/myBalance", "/myLoans", "myCards")
                            .authenticated()
                            .requestMatchers("/notices", "/contact","/register").permitAll())
                    .formLogin(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults());
            return http.build();
        }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager()
//    {
//        /*Approach 2 : where we use NoOptPasswordEncoder bean
//         * while creating user details*/
//
//        UserDetails admin = User.withUsername("admin")
//                .password("admin")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password("user")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource)
//    {
//        return new JdbcUserDetailsManager(dataSource);
//    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return  NoOpPasswordEncoder.getInstance();
    }

//        @Bean
//        public InMemoryUserDetailsManager userDetailsManager()
//        {
//            /*Approach 1 : where we use withDefaultPasswordEncoder
//             * while creating user details*/
//            UserDetails admin = User.withDefaultPasswordEncoder()
//                    .username("admin")
//                    .password("12345")
//                    .authorities("ADMIN")
//                    .build();
//            UserDetails user = User.withDefaultPasswordEncoder()
//                    .username("user")
//                    .password("12345")
//                    .authorities("USER")
//                    .build();
//            return new InMemoryUserDetailsManager(admin,user);
//        }


    //denying all after authentication
//    @Bean
//    SecurityFilterChain defaultSercurityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().denyAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//        return http.build();



}

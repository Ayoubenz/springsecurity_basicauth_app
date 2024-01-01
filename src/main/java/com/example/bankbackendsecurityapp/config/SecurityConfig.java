package com.example.bankbackendsecurityapp.config;

import com.example.bankbackendsecurityapp.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfig {


    //cors config
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
        @Bean
        SecurityFilterChain defaultSercurityFilterChain(HttpSecurity http) throws Exception {
        //csrf config
            CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
            requestHandler.setCsrfRequestAttributeName("_csrf");
            //cors and csrf implementation
            http.securityContext(context -> context
                            .requireExplicitSave(false))
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                    .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact","/register")
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                    .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
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
        return new BCryptPasswordEncoder();
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

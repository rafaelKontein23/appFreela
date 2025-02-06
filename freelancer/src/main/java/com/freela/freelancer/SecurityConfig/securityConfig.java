package com.freela.freelancer.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableMethodSecurity
@Configuration
public class securityConfig  {
    private static final String[] SweggerList = {"/swagger-ui/**","/v3/api-docs/**", "/swagger-resourse/**"};
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        try {
            httpSecurity.csrf(crsf -> crsf.disable() )
                    .authorizeHttpRequests(auth ->{
                        auth.requestMatchers("/trabalhador/cadastra").permitAll()
                                .requestMatchers("trabalhador/login").permitAll()
                                .requestMatchers("cidades/**").permitAll()
                                .requestMatchers("banco/**").permitAll()
                                .requestMatchers("/swagger-ui/index.html").permitAll().
                                requestMatchers(SweggerList).permitAll();
                        // aqui vc esta liberando acesso para todos acessar esse endPoints
                        auth.anyRequest().authenticated(); // aqui vc esta bloqueando esse endpoint. pq para acessar essa rota o usuario tem que esta logado por exemplo
                    });


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

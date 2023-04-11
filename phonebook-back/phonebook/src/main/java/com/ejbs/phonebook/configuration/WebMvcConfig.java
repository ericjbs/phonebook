package com.ejbs.phonebook.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir CORS para todos os endpoints
                .allowedOrigins("*") // Permitir solicitações de todas as origens
                .allowedMethods("*") // Permitir todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*"); // Permitir todos os cabeçalhos
    }
}


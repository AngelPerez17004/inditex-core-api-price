package com.inditex.core.config;

import com.inditex.core.domain.PriceServiceDomain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
    @Bean
    public PriceServiceDomain priceServiceDomain() {
        return new PriceServiceDomain();
    }
}

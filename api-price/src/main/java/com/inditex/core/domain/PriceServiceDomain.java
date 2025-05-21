package com.inditex.core.domain;

import com.inditex.core.config.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceServiceDomain {
    public Price validatePricePresent(Optional<Price> price) {
        return price.orElseThrow(() -> new PriceNotFoundException("No applicable price found"));
    }
}

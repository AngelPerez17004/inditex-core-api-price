package com.inditex.core.domain;

import com.inditex.core.domain.exception.PriceNotFoundException;

import java.util.Optional;

public class PriceServiceDomain {
    public Price validatePricePresent(Optional<Price> price) {
        return price.orElseThrow(() -> new PriceNotFoundException("No applicable price found"));
    }
}

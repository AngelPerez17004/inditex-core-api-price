package com.inditex.core.adapter.persistence.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PriceRateEntityTest {

    @Test
    void settersAndGetters_shouldAssignAndReturnCorrectValues() {
        PriceRateEntity entity = new PriceRateEntity();
        entity.setId(1L);
        entity.setCode("PRE");
        entity.setDescription("Tarifa Premium");
        entity.setPrice(BigDecimal.valueOf(38.95));
        entity.setCurrency("EUR");

        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getCode()).isEqualTo("PRE");
        assertThat(entity.getDescription()).isEqualTo("Tarifa Premium");
        assertThat(entity.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(38.95));
        assertThat(entity.getCurrency()).isEqualTo("EUR");
    }
}

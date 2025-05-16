package com.inditex.core.adapter.persistence.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceEntityTest {

    @Test
    void settersAndGetters_shouldAssignAndReturnCorrectValues() {
        PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setBrandId(1L);
        entity.setProductId(35455L);
        entity.setPriority(1);
        entity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        entity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));

        PriceRateEntity rate = new PriceRateEntity();
        rate.setId(1L);
        rate.setCode("PRE");

        entity.setPriceList(rate);

        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getBrandId()).isEqualTo(1L);
        assertThat(entity.getProductId()).isEqualTo(35455L);
        assertThat(entity.getPriority()).isEqualTo(1);
        assertThat(entity.getStartDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 0, 0));
        assertThat(entity.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59));
        assertThat(entity.getPriceList().getCode()).isEqualTo("PRE");
    }
}

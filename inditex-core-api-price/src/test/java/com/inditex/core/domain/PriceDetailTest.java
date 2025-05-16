package com.inditex.core.domain;

import com.inditex.core.infrastructure.util.enums.Currency;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PriceDetailTest {

    @Test
    void builder_givenValidValues_shouldBuildCorrectly() {

        String code = "STD";
        String description = "Standard Tariff";
        Currency currency = Currency.EUR;
        BigDecimal price = BigDecimal.valueOf(25.45);

        PriceDetail detail = PriceDetail.builder()
                .code(code)
                .description(description)
                .currency(currency)
                .price(price)
                .build();

        assertThat(detail.getCode()).isEqualTo(code);
        assertThat(detail.getDescription()).isEqualTo(description);
        assertThat(detail.getCurrency()).isEqualTo(currency);
        assertThat(detail.getPrice()).isEqualByComparingTo(price);
    }

    @Test
    void equalsAndHashCode_givenEqualObjects_shouldBeEqual() {
        PriceDetail d1 = PriceDetail.builder()
                .code("A")
                .description("desc")
                .currency(Currency.EUR)
                .price(BigDecimal.TEN)
                .build();

        PriceDetail d2 = PriceDetail.builder()
                .code("A")
                .description("desc")
                .currency(Currency.EUR)
                .price(BigDecimal.TEN)
                .build();

        assertThat(d1).isEqualTo(d2);
        assertThat(d1.hashCode()).isEqualTo(d2.hashCode());
    }
}

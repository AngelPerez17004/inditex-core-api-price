package com.inditex.core.infrastructure.validator.model;

import com.inditex.core.model.PriceDetailResponse;
import com.inditex.core.model.PriceDetailResponse.CurrencyEnum;
import com.inditex.core.model.RateResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class PriceDetailResponseTest {

    @Test
    void settersAndGetters_shouldAssignAndReturnCorrectValues() {
        RateResponse rate = new RateResponse().code("PRE").description("Premium");
        BigDecimal price = BigDecimal.valueOf(38.95);
        CurrencyEnum currency = CurrencyEnum.EUR;

        PriceDetailResponse detail = new PriceDetailResponse()
                .currency(currency)
                .price(price)
                .rate(rate);

        assertThat(detail.getCurrency()).isEqualTo(CurrencyEnum.EUR);
        assertThat(detail.getPrice()).isEqualByComparingTo(price);
        assertThat(detail.getRate()).isEqualTo(rate);
    }

    @Test
    void equalsAndHashCode_shouldWorkForEqualObjects() {
        PriceDetailResponse d1 = new PriceDetailResponse()
                .currency(CurrencyEnum.EUR)
                .price(BigDecimal.TEN)
                .rate(null);

        PriceDetailResponse d2 = new PriceDetailResponse()
                .currency(CurrencyEnum.EUR)
                .price(BigDecimal.TEN)
                .rate(null);

        assertThat(d1).isEqualTo(d2);
        assertThat(d1.hashCode()).isEqualTo(d2.hashCode());
    }

    @Test
    void currencyEnum_fromValue_shouldReturnEnum() {
        assertThat(CurrencyEnum.fromValue("EUR")).isEqualTo(CurrencyEnum.EUR);
        assertThat(CurrencyEnum.fromValue("USD")).isEqualTo(CurrencyEnum.USD);
    }

    @Test
    void currencyEnum_fromValue_shouldThrowForInvalidValue() {
        assertThatThrownBy(() -> CurrencyEnum.fromValue("INVALID"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unexpected value");
    }

    @Test
    void toString_shouldContainClassAndFields() {
        PriceDetailResponse detail = new PriceDetailResponse()
                .currency(CurrencyEnum.EUR)
                .price(BigDecimal.ONE);

        String toString = detail.toString();
        assertThat(toString).contains("PriceDetailResponse", "currency", "price");
    }
}

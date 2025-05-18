package com.inditex.core.infrastructure.validator.model;

import com.inditex.core.model.PriceDetailResponse;
import com.inditex.core.model.PriceResponse;
import com.inditex.core.model.RangeDateResponse;
import com.inditex.core.model.RateResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceResponseTest {

    @Test
    void settersAndGetters_shouldAssignAndReturnCorrectValues() {
        PriceDetailResponse detail = new PriceDetailResponse()
                .currency(PriceDetailResponse.CurrencyEnum.EUR)
                .rate(new RateResponse()
                        .code("PRE")
                        .description("Premium"))
                .price(BigDecimal.valueOf(38.95));

        RangeDateResponse range = new RangeDateResponse()
                .startDate(LocalDateTime.of(2020, 6, 15, 16, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

        PriceResponse response = new PriceResponse()
                .productId(35455L)
                .brandId(1L)
                .priority(1)
                .priceDetail(detail)
                .rangeDate(range);

        assertThat(response.getProductId()).isEqualTo(35455L);
        assertThat(response.getBrandId()).isEqualTo(1L);
        assertThat(response.getPriority()).isEqualTo(1);
        assertThat(response.getPriceDetail()).isEqualTo(detail);
        assertThat(response.getRangeDate()).isEqualTo(range);
    }

    @Test
    void equalsAndHashCode_shouldBeEqualForSameValues() {
        PriceResponse r1 = new PriceResponse()
                .productId(100L)
                .brandId(1L)
                .priority(0)
                .priceDetail(null)
                .rangeDate(null);

        PriceResponse r2 = new PriceResponse()
                .productId(100L)
                .brandId(1L)
                .priority(0)
                .priceDetail(null)
                .rangeDate(null);

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }

    @Test
    void toString_shouldContainAllFields() {
        PriceResponse response = new PriceResponse()
                .productId(123L)
                .brandId(99L)
                .priority(2);

        String result = response.toString();
        assertThat(result).contains("productId", "brandId", "priority");
    }
}

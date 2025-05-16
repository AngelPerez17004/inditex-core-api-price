package com.inditex.core.domain;

import com.inditex.core.infrastructure.util.enums.Currency;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceTest {

    @Test
    void builder_givenValidData_shouldBuildPriceCorrectly() {

        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        PriceDetail priceDetail = PriceDetail.builder()
                .price(BigDecimal.valueOf(25.45))
                .currency(Currency.EUR)
                .build();

        Price price = Price.builder()
                .id(1L)
                .brandId(1L)
                .productId(35455L)
                .priority(1)
                .startDate(start)
                .endDate(end)
                .priceDetail(priceDetail)
                .build();


        assertThat(price.getId()).isEqualTo(1L);
        assertThat(price.getBrandId()).isEqualTo(1L);
        assertThat(price.getProductId()).isEqualTo(35455L);
        assertThat(price.getPriority()).isEqualTo(1);
        assertThat(price.getStartDate()).isEqualTo(start);
        assertThat(price.getEndDate()).isEqualTo(end);
        assertThat(price.getPriceDetail()).isEqualTo(priceDetail);
    }

    @Test
    void equalsAndHashCode_shouldWorkForEqualObjects() {
        Price p1 = Price.builder().id(1L).brandId(1L).build();
        Price p2 = Price.builder().id(1L).brandId(1L).build();

        assertThat(p1).isEqualTo(p2);
        assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
    }

    @Test
    void toString_shouldReturnExpectedFormat() {
        Price price = Price.builder()
                .id(1L)
                .brandId(2L)
                .productId(3L)
                .priority(1)
                .priceDetail(null)
                .startDate(null)
                .endDate(null)
                .build();

        String expected = "Price(id=1, brandId=2, productId=3, priority=1, priceDetail=null, startDate=null, endDate=null)";
        assertThat(price.toString()).isEqualTo(expected);
    }
}

package com.inditex.core.application.service;

import com.inditex.core.application.port.persistence.ReadPricePort;
import com.inditex.core.config.exception.PriceNotFoundException;
import com.inditex.core.domain.Price;
import com.inditex.core.domain.PriceDetail;
import com.inditex.core.infrastructure.util.enums.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class GetApplicablePriceServiceTest {

    private ReadPricePort readPricePort;
    private GetApplicablePriceService service;

    @BeforeEach
    void setUp() {
        readPricePort = mock(ReadPricePort.class);
        service = new GetApplicablePriceService(readPricePort);
    }

    @Test
    void getApplicablePrice_givenMatchingPrice_shouldReturnFirstApplicable() {

        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price expectedPrice = Price.builder()
                .productId(35455L)
                .brandId(1L)
                .priority(0)
                .priceDetail(PriceDetail.builder()
                        .currency(Currency.EUR)
                        .price(new BigDecimal("35.5000"))
                        .code("PRE")
                        .description("Tarifa Premium")
                        .build())
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0,0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59,59))
                .build();

        when(readPricePort.findApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(List.of(expectedPrice));

        Price result = service.getApplicablePrice(applicationDate, productId, brandId);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedPrice);
        verify(readPricePort).findApplicablePrice(applicationDate, productId, brandId);
    }

    @Test
    void getApplicablePrice_givenEmptyResult_shouldThrowPriceNotFoundException() {

        Long productId = 35455L;
        Long brandId = 2L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(readPricePort.findApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(List.of());

        assertThatThrownBy(() -> service.getApplicablePrice(applicationDate, productId, brandId))
                .isInstanceOf(PriceNotFoundException.class)
                .hasMessage("Price not found");

        verify(readPricePort).findApplicablePrice(applicationDate, productId, brandId);
    }
}

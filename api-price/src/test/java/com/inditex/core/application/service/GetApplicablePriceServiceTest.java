package com.inditex.core.application.service;

import com.inditex.core.application.port.persistence.ReadPricePort;
import com.inditex.core.config.exception.PriceNotFoundException;
import com.inditex.core.domain.Price;
import com.inditex.core.domain.PriceDetail;
import com.inditex.core.domain.PriceServiceDomain;
import com.inditex.core.infrastructure.util.enums.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetApplicablePriceServiceTest {

    private ReadPricePort readPricePort;
    private PriceServiceDomain priceServiceDomain;
    private GetApplicablePriceService service;

    @BeforeEach
    void setUp() {
        readPricePort = mock(ReadPricePort.class);
        priceServiceDomain = mock(PriceServiceDomain.class);
        service = new GetApplicablePriceService(readPricePort, priceServiceDomain);
    }

    @Test
    void getApplicablePrice_givenMatchingPrice_shouldReturnFirstApplicable() {

        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        Price expectedPrice = Price.builder()
                .id(1L)
                .brandId(brandId)
                .productId(productId)
                .priority(1)
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .priceDetail(new PriceDetail("PRE", "Premium", Currency.EUR, new BigDecimal("25.00")))
                .build();

        when(readPricePort.findApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(Optional.of(expectedPrice));
        when(priceServiceDomain.validatePricePresent(Optional.of(expectedPrice)))
                .thenReturn(expectedPrice);

        Price result = service.getApplicablePrice(applicationDate, productId, brandId);

        assertNotNull(result);
        assertEquals(expectedPrice.getId(), result.getId());
    }

    @Test
    void getApplicablePrice_givenEmptyResult_shouldThrowPriceNotFoundException() {
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(readPricePort.findApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(Optional.empty());
        when(priceServiceDomain.validatePricePresent(Optional.empty()))
                .thenThrow(new PriceNotFoundException("No applicable price found"));

        assertThatThrownBy(() -> service.getApplicablePrice(applicationDate, productId, brandId))
                .isInstanceOf(PriceNotFoundException.class)
                .hasMessage("No applicable price found");

    }
}

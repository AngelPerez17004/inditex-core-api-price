
package com.inditex.core.application.service;

import com.inditex.core.application.port.persistence.ReadPricePort;
import com.inditex.core.application.usecase.GetApplicablePriceUseCase;
import com.inditex.core.domain.Price;
import com.inditex.core.domain.PriceServiceDomain;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GetApplicablePriceService implements GetApplicablePriceUseCase {

    private final ReadPricePort readPricePort;
    private final PriceServiceDomain priceServiceDomain;

    GetApplicablePriceService(ReadPricePort readPricePort, PriceServiceDomain priceServiceDomain) {
        this.readPricePort = readPricePort;
        this.priceServiceDomain = priceServiceDomain;
    }

    @Override
    public Price getApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        Optional<Price> price = readPricePort.findApplicablePrice(applicationDate, productId, brandId);
        return priceServiceDomain.validatePricePresent(price);
    }
}

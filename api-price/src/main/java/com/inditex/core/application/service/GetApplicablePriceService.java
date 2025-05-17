
package com.inditex.core.application.service;

import com.inditex.core.application.port.persistence.ReadPricePort;
import com.inditex.core.application.usecase.GetApplicablePriceUseCase;
import com.inditex.core.config.exception.PriceNotFoundException;
import com.inditex.core.domain.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetApplicablePriceService implements GetApplicablePriceUseCase {

    private final ReadPricePort readPricePort;

    GetApplicablePriceService(ReadPricePort readPricePort) {
        this.readPricePort = readPricePort;
    }

    @Override
    public Price getApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return readPricePort.findApplicablePrice(applicationDate, productId, brandId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }
}

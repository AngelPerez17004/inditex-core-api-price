package com.inditex.core.application.port.entrypoint.api;

import com.inditex.core.model.PriceResponse;

import java.time.LocalDateTime;

public interface FindPriceEndpointPort {

    PriceResponse getApplicablePrice(LocalDateTime applicationDate,
                                     Long productId,
                                     Long brandId);
}

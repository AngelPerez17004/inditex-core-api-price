package com.inditex.core.adapter.entrypoint.api;

import com.inditex.core.application.port.entrypoint.api.FindPriceEndpointPort;
import com.inditex.core.application.usecase.GetApplicablePriceUseCase;
import com.inditex.core.infrastructure.annotations.Adapter;
import com.inditex.core.model.PriceResponse;

import java.time.LocalDateTime;

@Adapter
public final class FindPriceEndpointAdapter implements FindPriceEndpointPort {
    private final GetApplicablePriceUseCase getApplicablePriceUseCase;
    private final PriceResponseMapper priceResponseMapper;

    FindPriceEndpointAdapter(GetApplicablePriceUseCase getApplicablePriceUseCase,
                             PriceResponseMapper priceResponseMapper) {
        this.getApplicablePriceUseCase = getApplicablePriceUseCase;
        this.priceResponseMapper = priceResponseMapper;
    }

    @Override
    public PriceResponse getApplicablePrice(LocalDateTime applicationDate,
                                            Long productId,
                                            Long brandId) {
        return priceResponseMapper.toResponse(getApplicablePriceUseCase.getApplicablePrice(applicationDate,
                productId,
                brandId));
    }
}

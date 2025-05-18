package com.inditex.core.adapter.entrypoint.api;

import com.inditex.core.domain.Price;
import com.inditex.core.infrastructure.annotations.Mapper;
import com.inditex.core.model.PriceDetailResponse;
import com.inditex.core.model.PriceResponse;
import com.inditex.core.model.RangeDateResponse;
import com.inditex.core.model.RateResponse;

@Mapper
class PriceResponseMapper {

    PriceResponseMapper() {
        super();
    }

    PriceResponse toResponse(Price price) {
        return new PriceResponse()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .priority(price.getPriority())
                .priceDetail(new PriceDetailResponse()
                        .currency(
                                PriceDetailResponse.CurrencyEnum.fromValue(price.getPriceDetail().getCurrency().name()))
                        .price(price.getPriceDetail().getPrice())
                        .rate(new RateResponse()
                                .code(price.getPriceDetail().getCode())
                                .description(price.getPriceDetail().getDescription()
                                )))
                .rangeDate(new RangeDateResponse()
                        .startDate(price.getStartDate())
                        .endDate(price.getEndDate()));
    }
}

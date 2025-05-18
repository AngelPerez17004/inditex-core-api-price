package com.inditex.core.adapter.persistence;

import com.inditex.core.adapter.persistence.model.PriceEntity;
import com.inditex.core.domain.Price;
import com.inditex.core.domain.PriceDetail;
import com.inditex.core.infrastructure.annotations.Mapper;
import com.inditex.core.infrastructure.util.enums.Currency;

@Mapper
class PriceH2Mapper {

    PriceH2Mapper() {
        super();
    }

    Price toDomain(PriceEntity priceEntity) {
        return Price.builder()
                .id(priceEntity.getId())
                .brandId(priceEntity.getBrandId())
                .productId(priceEntity.getProductId())
                .priority(priceEntity.getPriority())
                .priceDetail(PriceDetail.builder()
                        .code(priceEntity.getPriceList().getCode())
                        .description(priceEntity.getPriceList().getDescription())
                        .currency(Currency.valueOf(priceEntity.getPriceList().getCurrency()))
                        .price(priceEntity.getPriceList().getPrice())
                        .build())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .build();
    }

}

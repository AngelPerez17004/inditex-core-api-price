package com.inditex.core.adapter.persistence;

import com.inditex.core.adapter.persistence.model.PriceEntity;
import com.inditex.core.adapter.persistence.repository.PriceRepository;
import com.inditex.core.application.port.persistence.ReadPricePort;
import com.inditex.core.domain.Price;
import com.inditex.core.infrastructure.annotations.Adapter;

import java.time.LocalDateTime;
import java.util.List;

@Adapter
class ReadPriceAdapter implements ReadPricePort {

    private final PriceRepository priceRepository;

    private final PriceH2Mapper priceH2Mapper;

    public ReadPriceAdapter(PriceRepository priceRepository, PriceH2Mapper priceH2Mapper) {
        this.priceRepository = priceRepository;
        this.priceH2Mapper = priceH2Mapper;
    }


    @Override
    public List<Price> findApplicablePrice(LocalDateTime date, Long productId, Long brandId) {
        List<PriceEntity> rest = priceRepository.findApplicablePrice(productId, brandId, date);
        return priceRepository.findApplicablePrice(productId, brandId, date)
                .stream()
                .map(priceH2Mapper::toDomain)
                .toList();
    }
}

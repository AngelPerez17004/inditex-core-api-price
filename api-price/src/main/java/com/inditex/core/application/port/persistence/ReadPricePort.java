package com.inditex.core.application.port.persistence;

import com.inditex.core.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReadPricePort {

    Optional<Price> findApplicablePrice(LocalDateTime applicationDate,
                                        Long productId,
                                        Long brandId);

}

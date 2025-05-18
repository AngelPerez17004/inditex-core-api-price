package com.inditex.core.application.port.persistence;

import com.inditex.core.domain.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface ReadPricePort {

    List<Price> findApplicablePrice(LocalDateTime applicationDate,
                                    Long productId,
                                    Long brandId);

}

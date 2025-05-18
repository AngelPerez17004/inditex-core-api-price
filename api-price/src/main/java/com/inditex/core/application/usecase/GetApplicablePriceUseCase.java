
package com.inditex.core.application.usecase;

import com.inditex.core.domain.Price;

import java.time.LocalDateTime;

public interface GetApplicablePriceUseCase {
    Price getApplicablePrice(LocalDateTime applicationDate,
                             Long productId,
                             Long brandId);
}

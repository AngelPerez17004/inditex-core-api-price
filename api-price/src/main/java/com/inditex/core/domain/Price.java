package com.inditex.core.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Price {
    private Long id;
    private Long brandId;
    private Long productId;
    private Integer priority;
    private PriceDetail priceDetail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public boolean isApplicableTo(LocalDateTime date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
    public boolean isApplicableTo(Long productId, Long brandId) {
        return this.productId.equals(productId) && this.brandId.equals(brandId);
    }
}

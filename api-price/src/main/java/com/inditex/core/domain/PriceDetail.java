package com.inditex.core.domain;


import com.inditex.core.infrastructure.util.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class PriceDetail {
    private String code;
    private String description;
    private Currency currency;
    private BigDecimal price;
}

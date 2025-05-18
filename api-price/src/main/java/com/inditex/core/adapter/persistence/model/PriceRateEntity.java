package com.inditex.core.adapter.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "price_rate")
public class PriceRateEntity extends AuditableEntity {
    @Id
    @Column(name = "price_rate_id")
    private Long id;
    private String code;
    private String description;
    private BigDecimal price;
    private String currency;

}

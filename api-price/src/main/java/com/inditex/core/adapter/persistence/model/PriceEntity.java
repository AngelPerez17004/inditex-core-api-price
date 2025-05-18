package com.inditex.core.adapter.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "price")
public class PriceEntity extends AuditableEntity {
    @Id
    private Long id;
    private Long brandId;
    private Long productId;
    private Integer priority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_rate_id", referencedColumnName = "price_rate_id")
    private PriceRateEntity priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

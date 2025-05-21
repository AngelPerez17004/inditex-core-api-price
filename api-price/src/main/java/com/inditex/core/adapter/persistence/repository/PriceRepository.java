package com.inditex.core.adapter.persistence.repository;

import com.inditex.core.adapter.persistence.model.PriceEntity;
import com.inditex.core.infrastructure.util.constants.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Busca los precios aplicables según el producto, la marca y la fecha de aplicación.
     * Retorna una lista ordenada por prioridad descendente.
     *
     * @param productId identificador del producto
     * @param brandId identificador de la marca
     * @param dateQuery fecha de aplicación
     * @return lista de precios aplicables ordenados por prioridad
     */
    @Query(value = Constants.H2Query.FIND_APPLICABLE_PRICE)
    PriceEntity findApplicablePrice(@Param("productId") Long productId,
                                              @Param("brandId") Long brandId,
                                              @Param("dateQuery") LocalDateTime dateQuery);
}

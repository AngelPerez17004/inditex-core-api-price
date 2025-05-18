package com.inditex.core.infrastructure.util.constants;

public final class Constants {

    public static final class H2Query {
        public static final String FIND_APPLICABLE_PRICE = "SELECT p FROM PriceEntity p "
                + "    JOIN FETCH p.priceList "
                + "    WHERE p.productId = :productId "
                + "    AND p.brandId = :brandId "
                + "    AND :dateQuery BETWEEN p.startDate AND p.endDate "
                + "    ORDER BY p.priority DESC";
    }

}

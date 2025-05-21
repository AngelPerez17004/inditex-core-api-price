package com.inditex.core.adapter.entrypoint.api.res;


import com.inditex.core.api.PriceApiDelegate;
import com.inditex.core.application.port.entrypoint.api.FindPriceEndpointPort;
import com.inditex.core.model.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Slf4j
public class PriceApi implements PriceApiDelegate {

    private final FindPriceEndpointPort findPriceEndpointPort;

    /**
     * Constructor de la clase PriceApi.
     * @param findPriceEndpointPort adaptador para encontrar precios
     */
    public PriceApi(FindPriceEndpointPort findPriceEndpointPort) {
        this.findPriceEndpointPort = findPriceEndpointPort;
    }


    /**
     * Obtiene el precio aplicable según la fecha, producto y marca.
     * @param applicationDate fecha de aplicación
     * @param productId       identificador del producto
     * @param brandId         identificador de la marca
     * @return precio encontrado o excepción si no se encuentra
     */
    @Override
    public ResponseEntity<PriceResponse> getPriceByFilter(LocalDateTime applicationDate,
                                                          Long productId,
                                                          Long brandId) {
        log.info("PriceController - getPriceByFilter() | Incoming applicationDate :{}, productId :{}, brandId :{}",
                applicationDate,
                productId,
                brandId);
        PriceResponse applicablePriceResponse =
                findPriceEndpointPort.getApplicablePrice(applicationDate, productId, brandId);
        log.info("Returning price response :{}", applicablePriceResponse);
        return ResponseEntity.ok(applicablePriceResponse);
    }
}

package com.inditex.core.adapter.entrypoint.api.res;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PriceApiTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/api/v1/price/by-filters";
    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";

    @Test
    @DisplayName("Debe devolver tarifa base para 14-Jun-2020 10:00")
    void getPrice_validRequestOnMorning14June_shouldReturnBaseRate() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceDetail.price").value(35.5000));
    }

    @Test
    @DisplayName("Debe devolver tarifa promocional para 14-Jun-2020 16:00")
    void getPrice_validRequestOnAfternoon14June_shouldReturnPromotionalRate() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .param("applicationDate", "2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceDetail.price").value(25.45));
    }

    @Test
    @DisplayName("Debe devolver tarifa base para 14-Jun-2020 21:00")
    void getPrice_validRequestOnNight14June_shouldReturnBaseRate() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .param("applicationDate", "2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceDetail.price").value(35.50));
    }

    @Test
    @DisplayName("Debe devolver tarifa regular para 15-Jun-2020 10:00")
    void getPrice_validRequestOnMorning15June_shouldReturnRegularRate() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .param("applicationDate", "2020-06-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceDetail.price").value(30.50));
    }

    @Test
    @DisplayName("Debe devolver tarifa prsemium para 16-Jun-2020 21:00")
    void getPrice_validRequestOnNight16June_shouldReturnPremiumRate() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .param("applicationDate", "2020-06-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceDetail.price").value(38.95));
    }

    @Test
    @DisplayName("Debe retornar 404 cuando no se encuentra tarifa aplicable")
    void getPrice_nonExistentProduct_shouldReturn404() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("productId", "99999")
                        .param("brandId", BRAND_ID)
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isNotFound());
    }
}

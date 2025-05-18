package com.inditex.core.infrastructure.validator.model;

import com.inditex.core.model.RateResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateResponseTest {

    @Test
    void settersAndGetters_shouldAssignAndReturnCorrectValues() {
        RateResponse rate = new RateResponse()
                .code("PRE")
                .description("Tarifa Premium");

        assertThat(rate.getCode()).isEqualTo("PRE");
        assertThat(rate.getDescription()).isEqualTo("Tarifa Premium");
    }

    @Test
    void equalsAndHashCode_shouldWorkForEqualObjects() {
        RateResponse r1 = new RateResponse().code("STD").description("Standard");
        RateResponse r2 = new RateResponse().code("STD").description("Standard");

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }

    @Test
    void toString_shouldContainAllFields() {
        RateResponse rate = new RateResponse().code("PRO").description("Promoción");

        String output = rate.toString();
        assertThat(output).contains("code", "description", "PRO", "Promoción");
    }
}

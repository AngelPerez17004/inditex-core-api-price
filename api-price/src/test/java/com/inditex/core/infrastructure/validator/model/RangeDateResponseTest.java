package com.inditex.core.infrastructure.validator.model;

import com.inditex.core.model.RangeDateResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RangeDateResponseTest {

    @Test
    void settersAndGetters_shouldAssignAndReturnCorrectValues() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 10, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        RangeDateResponse response = new RangeDateResponse()
                .startDate(start)
                .endDate(end);

        assertThat(response.getStartDate()).isEqualTo(start);
        assertThat(response.getEndDate()).isEqualTo(end);
    }

    @Test
    void equalsAndHashCode_shouldWorkForEqualObjects() {
        LocalDateTime now = LocalDateTime.now();

        RangeDateResponse r1 = new RangeDateResponse()
                .startDate(now)
                .endDate(now.plusHours(2));

        RangeDateResponse r2 = new RangeDateResponse()
                .startDate(now)
                .endDate(now.plusHours(2));

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }

    @Test
    void toString_shouldContainStartAndEndDates() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 10, 0);
        LocalDateTime end = LocalDateTime.of(2020, 6, 14, 22, 0);

        RangeDateResponse response = new RangeDateResponse()
                .startDate(start)
                .endDate(end);

        String output = response.toString();
        assertThat(output).contains("startDate", "endDate", "2020-06-14");
    }
}

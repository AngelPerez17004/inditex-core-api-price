package com.inditex.core.infrastructure.validator;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ObjectValidatorTest {

    private static class TestObject {

        @NotNull
        private final Long id;

        private TestObject(Long id) {
            this.id = id;
        }
    }

    @Test
    public void shouldThrowException_whenSomeFieldsAreNotPassingAnnotationValidations() {
        TestObject testObject = new TestObject(null);

        assertThatThrownBy(() -> ObjectValidator.validate(testObject))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void shouldPassValidationWithNoException_whenAllFieldsPassesAnnotationValidations() {
        TestObject testObject = new TestObject(11L);

        ObjectValidator.validate(testObject);
    }
}
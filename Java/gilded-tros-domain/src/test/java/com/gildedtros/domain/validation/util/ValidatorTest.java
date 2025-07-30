package com.gildedtros.domain.validation.util;

import com.gildedtros.domain.Item;
import com.gildedtros.domain.validation.rules.QualityValidationRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {

    @Test
    @DisplayName("Given an item validator with one rule, when validated, then it should do validation")
    void givenItemValidatorWithOneRule_whenValidated_thenDoValidation() {
        // GIVEN
        Item item = new Item("StandardItem", 10, 100);
        Validator validator = new Validator().addRule(new QualityValidationRule());

        // WHEN
        Outcome<Item> outcome = validator.validate(item);

        // THEN
        assertThat(outcome)
                .isInstanceOf(Outcome.Failure.class)
                .extracting(failure -> ((Outcome.Failure<Item>) failure).getMessage())
                .isEqualTo("Quality must be between 0 and 50. Provided quality: 100");
    }

    @Test
    @DisplayName("Given an validator with no validation rules, when validation is called, then throw exception")
    void givenValidatorWithNoRules_whenValidationCalled_thenThrowException() {
        // GIVEN
        Item item = new Item("StandardItem", 10, 100);
        Validator validator = new Validator();

        // WHEN & THEN
        assertThatThrownBy(() -> validator.validate(item))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("No validation rules have been added");
    }

    @Test
    @DisplayName("Given an validator with null as rule, when validation is called, then throw exception")
    void givenValidatorWithNullRule_whenValidationCalled_thenThrowException() {
        // GIVEN
        Validator validator = new Validator();

        // WHEN & THEN
        assertThatThrownBy(() -> validator.addRule(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Validation rule cannot be null");
    }

}
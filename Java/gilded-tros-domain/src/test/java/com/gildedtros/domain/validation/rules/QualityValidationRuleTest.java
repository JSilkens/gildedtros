package com.gildedtros.domain.validation.rules;

import com.gildedtros.domain.Item;
import com.gildedtros.domain.validation.rules.QualityValidationRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QualityValidationRuleTest {

    @Test
    @DisplayName("Given an item with quality less than 0, when validate is called, then it should add an error")
    void givenItemWithQualityLessThanZero_whenValidate_thenAddError() {
        // GIVEN
        Item item = new Item("Standard Item", 10, -1);
        QualityValidationRule rule = new QualityValidationRule();
        List<String> errors = new ArrayList<>();

        // WHEN
        rule.validate(item, errors);

        // THEN
        assertThat(errors)
                .hasSize(1)
                .contains("Quality must be between 0 and 50. Provided quality: -1");
    }

    @Test
    @DisplayName("Given an item with quality greater than 50, when validate is called, then it should add an error")
    void givenItemWithQualityGreaterThanFifty_whenValidate_thenAddError() {
        // GIVEN
        Item item = new Item("Standard Item", 10, 51);
        QualityValidationRule rule = new QualityValidationRule();
        List<String> errors = new ArrayList<>();

        // WHEN
        rule.validate(item, errors);

        // THEN
        assertThat(errors)
                .hasSize(1)
                .contains("Quality must be between 0 and 50. Provided quality: 51");
    }

    @Test
    @DisplayName("Given an item with quality within valid range, when validate is called, then it should not add any error")
    void givenItemWithQualityWithinValidRange_whenValidate_thenNoError() {
        // GIVEN
        Item item = new Item("Standard Item", 10, 25);
        QualityValidationRule rule = new QualityValidationRule();
        List<String> errors = new ArrayList<>();

        // WHEN
        rule.validate(item, errors);

        // THEN
        assertThat(errors).isEmpty();
    }
}
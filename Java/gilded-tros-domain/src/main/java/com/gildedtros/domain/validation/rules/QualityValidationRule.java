package com.gildedtros.domain.validation.rules;

import com.gildedtros.domain.Item;
import com.gildedtros.domain.validation.util.ValidationRule;

import java.util.List;

public class QualityValidationRule implements ValidationRule {

    @Override
    public void validate(Item item, List<String> errors) {
        if (item.quality < 0 || item.quality > 50) {;
            errors.add(String.format("Quality must be between 0 and 50. Provided quality: %s" , item.quality));
        }
    }
}

package com.gildedtros.domain.validation.util;

import com.gildedtros.domain.Item;

import java.util.List;

public interface ValidationRule {
    void validate(Item item , List<String> errors);
}

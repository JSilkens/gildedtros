package com.gildedtros.domain.validation.util;

import com.gildedtros.domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Validator {
    private final List<ValidationRule> rules = new ArrayList<>();

    public Validator addRule(ValidationRule rule) {
        if (Objects.isNull(rule)) {
            throw new IllegalArgumentException("Validation rule cannot be null");
        }
        rules.add(rule);
        return this;
    }

    public Outcome<Item> validate(Item item) {
        if (rules.isEmpty()) {
            throw new IllegalStateException("No validation rules have been added");
        }
        List<String> errors = rules.stream()
                .flatMap(rule -> {
                    List<String> ruleErrors = new ArrayList<>();
                    rule.validate(item, ruleErrors);
                    return ruleErrors.stream();
                })
                .collect(Collectors.toList());

       if (errors.isEmpty()) {
           return new Outcome.Success(item);
       }else{
           return new Outcome.Failure<>(String.join(", ", errors));
       }
    }
}

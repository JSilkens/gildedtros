package com.gildedtros.domain.factory;

import com.gildedtros.domain.*;
import com.gildedtros.domain.validation.rules.QualityValidationRule;
import com.gildedtros.domain.validation.util.Outcome;
import com.gildedtros.domain.validation.util.Validator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemFactory {

    public static Outcome<Item> createItem(String name, Integer sellIn, Integer quality) {
        Item item = new Item(name, sellIn, quality);

        return new Validator()
                .addRule(new QualityValidationRule())
                .validate(item);
    }

    public static Outcome<Item> createSmellyItem(String name, Integer sellIn, Integer quality) {
        SmellyItem smellyItem = new SmellyItem(name, sellIn, quality);

        return new Validator()
                .addRule(new QualityValidationRule())
                .validate(smellyItem);
    }

    public static Outcome<Item> createLegendaryItem(String name, Integer sellIn) {
        LegendaryItem legendaryItem = new LegendaryItem(name, sellIn);

        return new Outcome.Success<>(legendaryItem);
    }

    public static Outcome<Item> createBackstagePass(String name, Integer sellIn, Integer quality) {
        BackstagePass backstagePassItem = new BackstagePass(name, sellIn, quality);

        return new Validator()
                .addRule(new QualityValidationRule())
                .validate(backstagePassItem);
    }

    public static Outcome<Item> createGoodWine(String name, Integer sellIn, Integer quality) {
        GoodWine goodWine = new GoodWine(name, sellIn, quality);

        return new Validator()
                .addRule(new QualityValidationRule())
                .validate(goodWine);
    }
}
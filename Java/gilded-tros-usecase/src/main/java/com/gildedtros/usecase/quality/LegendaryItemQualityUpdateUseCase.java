package com.gildedtros.usecase.quality;

import com.gildedtros.domain.Item;

public class LegendaryItemQualityUpdateUseCase implements QualityUpdateUseCase {
    @Override
    public void invoke(Item item) {
        // Legendary items do not degrade in quality
        item.sellIn -= 1;
    }
}
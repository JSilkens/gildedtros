package com.gildedtros.usecase.quality;

import com.gildedtros.domain.Item;

public class StandardItemQualityUpdateUseCase implements QualityUpdateUseCase {
    @Override
    public void invoke(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
        item.sellIn -= 1;
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality -= 1;
        }
    }
}
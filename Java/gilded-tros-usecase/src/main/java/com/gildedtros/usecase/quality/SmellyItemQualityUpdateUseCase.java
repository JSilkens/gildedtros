package com.gildedtros.usecase.quality;

import com.gildedtros.domain.Item;
import com.gildedtros.domain.SmellyItem;

public class SmellyItemQualityUpdateUseCase implements QualityUpdateUseCase {
    @Override
    public void invoke(Item item) {
        if (item instanceof SmellyItem) {
            if (item.quality > 0) {
                item.quality -= 2;
            }
            item.sellIn -= 1;
            if (item.sellIn < 0 && item.quality > 0) {
                item.quality -= 2;
            }
        }
    }
}
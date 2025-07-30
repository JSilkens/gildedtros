package com.gildedtros.usecase.quality;

import com.gildedtros.domain.Item;

public class BackstagePassUpdateQualityUseCase implements QualityUpdateUseCase {

    @Override
    public void invoke(Item item) {
        if (item.quality < 50) {
            if (item.sellIn > 10) {
                item.quality += 1;
            } else if (item.sellIn > 5) {
                item.quality += 2;
            } else if (item.sellIn > 0) {
                item.quality += 3;
            } else {
                item.quality = 0;
            }
            item.quality = Math.min(item.quality, 50);
        }
        item.sellIn -= 1;
    }
}
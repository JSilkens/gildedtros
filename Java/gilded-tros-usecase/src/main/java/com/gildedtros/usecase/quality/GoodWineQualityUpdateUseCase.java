package com.gildedtros.usecase.quality;

import com.gildedtros.domain.Item;

public class GoodWineQualityUpdateUseCase implements QualityUpdateUseCase {

    @Override
    public void invoke(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
        item.sellIn -= 1;
    }
}
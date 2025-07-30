package com.gildedtros.usecase;

import com.gildedtros.domain.*;
import com.gildedtros.usecase.quality.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GildedTros {
    private final BackstagePassUpdateQualityUseCase backstagePassUpdateQualityUseCase = new BackstagePassUpdateQualityUseCase();
    private final GoodWineQualityUpdateUseCase goodWineQualityUpdateUseCase = new GoodWineQualityUpdateUseCase();
    private final LegendaryItemQualityUpdateUseCase legendaryItemUpdateQualityUseCase = new LegendaryItemQualityUpdateUseCase();
    private final SmellyItemQualityUpdateUseCase smellyItemQualityUpdateUseCase = new SmellyItemQualityUpdateUseCase();
    private final StandardItemQualityUpdateUseCase standardItemQualityUpdateUseCase = new StandardItemQualityUpdateUseCase();


    public Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            QualityUpdateUseCase qualityUpdateUseCase = switch (item) {
                case BackstagePass b -> backstagePassUpdateQualityUseCase;
                case GoodWine w -> goodWineQualityUpdateUseCase;
                case LegendaryItem l -> legendaryItemUpdateQualityUseCase;
                case SmellyItem s -> smellyItemQualityUpdateUseCase;
                default -> standardItemQualityUpdateUseCase;
            };
            qualityUpdateUseCase.invoke(item);
        }
    }
}
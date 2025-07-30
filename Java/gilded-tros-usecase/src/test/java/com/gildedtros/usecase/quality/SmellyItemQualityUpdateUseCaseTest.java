package com.gildedtros.usecase.quality;

import com.gildedtros.domain.Item;
import com.gildedtros.domain.SmellyItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SmellyItemQualityUpdateUseCaseTest {

    private final SmellyItemQualityUpdateUseCase useCase = new SmellyItemQualityUpdateUseCase();

    @Test
    @DisplayName("Given a smelly item with positive quality before sell-in date, when invoke is called, it should degrade quality by 2 and sellIn by 1")
    void givenSmellyItemWithPositiveQualityBeforeSellInDate_whenInvoke_thenDegradeQualityAndSellIn() {
        // GIVEN
        SmellyItem item = new SmellyItem("Smelly Item", 10, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(18);
        assertThat(item.sellIn).isEqualTo(9);
    }

    @Test
    @DisplayName("Given a smelly item with positive quality after sell-in date, when invoke is called, it should degrade quality by 4 and sellIn by 1")
    void givenSmellyItemWithPositiveQualityAfterSellInDate_whenInvoke_thenDegradeQualityByFourAndSellInByOne() {
        // GIVEN
        SmellyItem item = new SmellyItem("Smelly Item", 0, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(16);
        assertThat(item.sellIn).isEqualTo(-1);
    }

    @Test
    @DisplayName("Given a smelly item with zero quality, when invoke is called, it should not degrade quality but reduce sellIn by 1")
    void givenSmellyItemWithZeroQuality_whenInvoke_thenNoQualityDegradationButSellInDecreases() {
        // GIVEN
        SmellyItem item = new SmellyItem("Smelly Item", 10, 0);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(9);
    }

    @Test
    @DisplayName("Given a standard item, when invoke is called, it should not affect the item")
    void givenStandardItem_whenInvoke_thenDoNotAffectItem() {
        // GIVEN
        Item item = new Item("Standard Item", 10, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(20);
        assertThat(item.sellIn).isEqualTo(10);
    }
}
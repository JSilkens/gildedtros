package com.gildedtros.usecase.quality;

import com.gildedtros.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StandardItemQualityUpdateUseCaseTest {

    private final StandardItemQualityUpdateUseCase useCase = new StandardItemQualityUpdateUseCase();

    @Test
    @DisplayName("Given an item with positive quality before sell-in date, when invoke is called, then reduce quality by 1 and sellIn by 1")
    void givenItemWithPositiveQualityBeforeSellInDate_whenInvoke_thenReduceQualityAndSellInBy1() {
        // GIVEN
        Item item = new Item("Standard Item", 10, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(19);
        assertThat(item.sellIn).isEqualTo(9);
    }

    @Test
    @DisplayName("Given an item with positive quality after sell-in date, when invoke is called, then reduce quality by 2 and sellIn by 1")
    void givenItemWithPositiveQualityAfterSellInDate_whenInvoke_thenReduceQualityBy2AndSellInBy1() {
        // GIVEN
        Item item = new Item("Standard Item", 0, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(18);
        assertThat(item.sellIn).isEqualTo(-1);
    }

    @Test
    @DisplayName("Given an item with zero quality, when invoke is called, then quality remains 0 but sellIn reduces by 1")
    void givenItemWithZeroQuality_whenInvoke_thenQualityRemainsZeroAndSellInReducesBy1() {
        // GIVEN
        Item item = new Item("Standard Item", 10, 0);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(9);
    }

    @Test
    @DisplayName("Given an item after sell-in date with zero quality, when invoke is called, then quality remains 0 and sellIn reduces by 1")
    void givenItemAfterSellInDateWithZeroQuality_whenInvoke_thenQualityRemainsZeroAndSellInReducesBy1() {
        // GIVEN
        Item item = new Item("Standard Item", -1, 0);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-2);
    }

    @Test
    @DisplayName("Given an item with a SellIn value of 0, when a day passes, it should decrease quality by 2")
    void givenItemWithSellInZero_whenDayPasses_thenDecreaseQualityBy2() {
        // GIVEN
        Item item = new Item("Standard Item", 0, 10);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(8);
        assertThat(item.sellIn).isEqualTo(-1);
    }
}
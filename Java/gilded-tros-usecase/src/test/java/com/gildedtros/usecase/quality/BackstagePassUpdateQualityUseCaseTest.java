package com.gildedtros.usecase.quality;

import com.gildedtros.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BackstagePassUpdateQualityUseCaseTest {

    private final BackstagePassUpdateQualityUseCase useCase = new BackstagePassUpdateQualityUseCase();

    @Test
    @DisplayName("Given a backstage pass with more than 10 days remaining, when invoke is called, then increase quality by 1")
    void givenBackstagePassWithMoreThanTenDays_whenInvoke_thenIncreaseQualityByOne() {
        // GIVEN
        Item item = new Item("Backstage Pass", 11, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(21);
        assertThat(item.sellIn).isEqualTo(10);
    }

    @Test
    @DisplayName("Given a backstage pass with 10 days or less remaining, when invoke is called, then increase quality by 2")
    void givenBackstagePassWithTenDaysOrLess_whenInvoke_thenIncreaseQualityByTwo() {
        // GIVEN
        Item item = new Item("Backstage Pass", 10, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(22);
        assertThat(item.sellIn).isEqualTo(9);
    }

    @Test
    @DisplayName("Given a backstage pass with 5 days or less remaining, when invoke is called, then increase quality by 3")
    void givenBackstagePassWithFiveDaysOrLess_whenInvoke_thenIncreaseQualityByThree() {
        // GIVEN
        Item item = new Item("Backstage Pass", 5, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(23);
        assertThat(item.sellIn).isEqualTo(4);
    }

    @Test
    @DisplayName("Given a backstage pass after the concert, when invoke is called, then quality drops to 0")
    void givenBackstagePassAfterConcert_whenInvoke_thenQualityDropsToZero() {
        // GIVEN
        Item item = new Item("Backstage Pass", 0, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-1);
    }

    @Test
    @DisplayName("Given a backstage pass with quality close to max, when invoke is called, then quality does not exceed 50")
    void givenBackstagePassWithQualityCloseToMax_whenInvoke_thenQualityDoesNotExceedFifty() {
        // GIVEN
        Item item = new Item("Backstage Pass", 5, 49);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(50);
        assertThat(item.sellIn).isEqualTo(4);
    }
}
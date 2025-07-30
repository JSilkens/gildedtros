package com.gildedtros.usecase.quality;

import com.gildedtros.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GoodWineQualityUpdateUseCaseTest {

    private final GoodWineQualityUpdateUseCase useCase = new GoodWineQualityUpdateUseCase();

    @Test
    @DisplayName("Given a Good Wine item, when invoke is called, it should increase quality by 1 each day")
    void givenGoodWine_whenInvoke_thenIncreaseQualityByOne() {
        // GIVEN
        Item item = new Item("Good Wine", 10, 20);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(21);
        assertThat(item.sellIn).isEqualTo(9);
    }

    @Test
    @DisplayName("Given a Good Wine item with max quality, when invoke is called, its quality should not exceed 50")
    void givenGoodWineWithMaxQuality_whenInvoke_thenQualityDoesNotExceedFifty() {
        // GIVEN
        Item item = new Item("Good Wine", 10, 50);

        // WHEN
        useCase.invoke(item);

        // THEN
        assertThat(item.quality).isEqualTo(50);
        assertThat(item.sellIn).isEqualTo(9);
    }
}
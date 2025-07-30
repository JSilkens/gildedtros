package com.gildedtros.domain.factory;

import com.gildedtros.domain.Item;
import com.gildedtros.domain.LegendaryItem;
import com.gildedtros.domain.SmellyItem;
import com.gildedtros.domain.validation.util.Outcome;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemFactoryTest {

    @Test
    @DisplayName("Given an item, when createItem is called, then it should return the item")
    void givenItem_whenCreateItem_thenReturnItem() {
        // GIVEN
        Item expectedItem = new Item("StandardItem", 10, 50);

        // WHEN
        Outcome<Item> actualItem = ItemFactory.createItem("StandardItem", 10, 50);

        //THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Success.class)
                .satisfies(itemOutcome -> itemOutcome.toOptional()
                        .ifPresent(item -> {
                            assertEquals(expectedItem.name, item.name);
                            assertEquals(expectedItem.quality, item.quality);
                            assertEquals(expectedItem.sellIn, item.sellIn);
                        }));
    }

    @Test
    @DisplayName("Given an item with quality higher than fifty, when createItem is called, then it should return a failure")
    void givenItemWithQualityHigherThanFifty_whenCreateItem_thenReturnFailure() {
        // GIVEN & WHEN
        Outcome<Item> actualItem = ItemFactory.createItem("StandardItem", 10, 100);

        //THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Failure.class)
                .extracting(failure -> ((Outcome.Failure<Item>) failure).getMessage())
                .isEqualTo("Quality must be between 0 and 50. Provided quality: 100");


    }

    @Test
    @DisplayName("Given an item with a negative quality, when createItem is called, then it should return a failure")
    void givenItemWithNegativeQuality_whenCreateItem_thenReturnFailure() {
        // GIVEN & WHEN
        Outcome<Item> actualItem = ItemFactory.createItem("StandardItem", 10, -1);

        //THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Failure.class)
                .extracting(failure -> ((Outcome.Failure<Item>) failure).getMessage())
                .isEqualTo("Quality must be between 0 and 50. Provided quality: -1");


    }

    @Test
    @DisplayName("Given a smelly item, when createSmellyItem is called, then it should return the smelly item")
    void givenSmellyItem_whenCreateSmellyItem_thenReturnItem() {
        // GIVEN
        SmellyItem expectedItem = new SmellyItem("SmellyItem", 10, 50);

        // WHEN
        Outcome<Item> actualItem = ItemFactory.createSmellyItem("SmellyItem", 10, 50);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Success.class)
                .satisfies(itemOutcome -> itemOutcome.toOptional()
                        .ifPresent(item -> {
                            assertEquals(expectedItem.name, item.name);
                            assertEquals(expectedItem.quality, item.quality);
                            assertEquals(expectedItem.sellIn, item.sellIn);
                        }));
    }

    @Test
    @DisplayName("Given a smelly item with quality higher than fifty, when createSmellyItem is called, then it should return a failure")
    void givenSmellyItemWithQualityHigherThanFifty_whenCreateSmellyItem_thenReturnFailure() {
        // GIVEN & WHEN
        Outcome<Item> actualItem = ItemFactory.createSmellyItem("SmellyItem", 10, 100);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Failure.class)
                .extracting(failure -> ((Outcome.Failure<Item>) failure).getMessage())
                .isEqualTo("Quality must be between 0 and 50. Provided quality: 100");
    }

    @Test
    @DisplayName("Given a smelly item with negative quality, when createSmellyItem is called, then it should return a failure")
    void givenSmellyItemWithNegativeQuality_whenCreateSmellyItem_thenReturnFailure() {
        // GIVEN & WHEN
        Outcome<Item> actualItem = ItemFactory.createSmellyItem("SmellyItem", 10, -1);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Failure.class)
                .extracting(failure -> ((Outcome.Failure<Item>) failure).getMessage())
                .isEqualTo("Quality must be between 0 and 50. Provided quality: -1");
    }

    @Test
    @DisplayName("Given a legendary item, when createLegendaryItem is called, then it should return the legendary item")
    void givenLegendaryItem_whenCreateLegendaryItem_thenReturnLegendaryItem() {
        // GIVEN
        LegendaryItem expectedItem = new LegendaryItem("LegendaryItem", 10);

        // WHEN
        Outcome<Item> actualItem = ItemFactory.createLegendaryItem("LegendaryItem", 10);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Success.class)
                .satisfies(itemOutcome -> itemOutcome.toOptional()
                        .ifPresent(item -> {
                            assertEquals(expectedItem.name, item.name);
                            assertEquals(80, item.quality); // Assuming legendary items have fixed quality of 80
                            assertEquals(expectedItem.sellIn, item.sellIn);
                        }));
    }

    @Test
    @DisplayName("Given a backstage pass with valid quality, when createBackstagePass is called, then it should return the backstage pass")
    void givenBackstagePassWithValidQuality_whenCreateBackstagePass_thenReturnItem() {
        // GIVEN
        Item expectedItem = new Item("Backstage Pass", 15, 20);

        // WHEN
        Outcome<Item> actualItem = ItemFactory.createBackstagePass("Backstage Pass", 15, 20);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Success.class)
                .satisfies(itemOutcome -> itemOutcome.toOptional()
                        .ifPresent(item -> {
                            assertEquals(expectedItem.name, item.name);
                            assertEquals(expectedItem.quality, item.quality);
                            assertEquals(expectedItem.sellIn, item.sellIn);
                        }));
    }

    @Test
    @DisplayName("Given a backstage pass with quality higher than fifty, when createBackstagePass is called, then it should return a failure")
    void givenBackstagePassWithQualityHigherThanFifty_whenCreateBackstagePass_thenReturnFailure() {
        // GIVEN & WHEN
        Outcome<Item> actualItem = ItemFactory.createBackstagePass("Backstage Pass", 15, 55);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Failure.class)
                .extracting(failure -> ((Outcome.Failure<Item>) failure).getMessage())
                .isEqualTo("Quality must be between 0 and 50. Provided quality: 55");
    }

    @Test
    @DisplayName("Given a backstage pass with negative quality, when createBackstagePass is called, then it should return a failure")
    void givenBackstagePassWithNegativeQuality_whenCreateBackstagePass_thenReturnFailure() {
        // GIVEN & WHEN
        Outcome<Item> actualItem = ItemFactory.createBackstagePass("Backstage Pass", 15, -5);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Failure.class)
                .extracting(failure -> ((Outcome.Failure<Item>) failure).getMessage())
                .isEqualTo("Quality must be between 0 and 50. Provided quality: -5");
    }

    @Test
    @DisplayName("Given a Good Wine with valid quality, when createGoodWine is called, then it should return the Good Wine")
    void givenGoodWineWithValidQuality_whenCreateGoodWine_thenReturnItem() {
        // GIVEN
        Item expectedItem = new Item("Good Wine", 15, 20);

        // WHEN
        Outcome<Item> actualItem = ItemFactory.createGoodWine("Good Wine", 15, 20);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Success.class)
                .satisfies(itemOutcome -> itemOutcome.toOptional()
                        .ifPresent(item -> {
                            assertEquals(expectedItem.name, item.name);
                            assertEquals(expectedItem.quality, item.quality);
                            assertEquals(expectedItem.sellIn, item.sellIn);
                        }));
    }

    @Test
    @DisplayName("Given a Good Wine with quality higher than fifty, when createGoodWine is called, then it should return a failure")
    void givenGoodWineWithQualityHigherThanFifty_whenCreateGoodWine_thenReturnFailure() {
        // GIVEN & WHEN
        Outcome<Item> actualItem = ItemFactory.createGoodWine("Good Wine", 15, 55);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Failure.class)
                .extracting(failure -> ((Outcome.Failure<Item>) failure).getMessage())
                .isEqualTo("Quality must be between 0 and 50. Provided quality: 55");
    }

    @Test
    @DisplayName("Given a Good Wine with negative quality, when createGoodWine is called, then it should return a failure")
    void givenGoodWineWithNegativeQuality_whenCreateGoodWine_thenReturnFailure() {
        // GIVEN & WHEN
        Outcome<Item> actualItem = ItemFactory.createGoodWine("Good Wine", 15, -5);

        // THEN
        Assertions.assertThat(actualItem)
                .isInstanceOf(Outcome.Failure.class)
                .extracting(failure -> ((Outcome.Failure<Item>) failure).getMessage())
                .isEqualTo("Quality must be between 0 and 50. Provided quality: -5");
    }

}
package com.gildedtros.domain.factory;

import com.gildedtros.domain.Item;
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

}
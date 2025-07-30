package com.gildedtros.app;

import com.gildedtros.domain.*;
import com.gildedtros.usecase.GildedTros;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedTrosTest {

    private GildedTros gildedTros;

    @Test
    @DisplayName("Given an array of items, when updateQuality is called, then it should update according to specific rules per item type")
    void givenArrayOfItems_whenUpdateQualityCalled_thenVerifyQualityUpdates() {
        // GIVEN
        Item[] items = new Item[]{
                new BackstagePass("Backstage Pass", 11, 20),
                new GoodWine("Good Wine", 10, 20),
                new LegendaryItem("Legendary Item", 5),
                new SmellyItem("Smelly Item", 5, 20),
                new Item("Standard Item", 5, 20)
        };

        gildedTros = new GildedTros(items);

        // WHEN
        gildedTros.updateQuality();

        // THEN
        assertThat(items[0].quality).isEqualTo(21); // Backstage Pass quality increases by 1
        assertThat(items[1].quality).isEqualTo(21); // Good Wine quality increases by 1
        assertThat(items[2].quality).isEqualTo(80); // Legendary Item quality stays 80
        assertThat(items[3].quality).isEqualTo(18); // Smelly Item quality decreases by 2
        assertThat(items[4].quality).isEqualTo(19); // Standard Item quality decreases by 1

        assertThat(items[0].sellIn).isEqualTo(10);  // SellIn decreased by 1 for all
        assertThat(items[1].sellIn).isEqualTo(9);
        assertThat(items[2].sellIn).isEqualTo(4);
        assertThat(items[3].sellIn).isEqualTo(4);
        assertThat(items[4].sellIn).isEqualTo(4);
    }

    @Test
    @DisplayName("Given items past their sell date, when updateQuality is called, quality should update according to past-sell rules")
    void givenItemsPastSellDate_whenUpdateQualityCalled_thenVerifyQualityUpdatesAccordingly() {
        // GIVEN
        Item[] items = new Item[]{
                new BackstagePass("Backstage Pass", 0, 20),
                new GoodWine("Good Wine", -1, 20),
                new SmellyItem("Smelly Item", -1, 20),
                new Item("Standard Item", -1, 20)
        };

        gildedTros = new GildedTros(items);

        // WHEN
        gildedTros.updateQuality();

        // THEN
        assertThat(items[0].quality).isEqualTo(0);  // Backstage Pass drops to 0
        assertThat(items[1].quality).isEqualTo(21); // Good Wine increases by 1
        assertThat(items[2].quality).isEqualTo(16); // Smelly Item decreases by 4
        assertThat(items[3].quality).isEqualTo(18); // Standard Item decreases by 2

        assertThat(items[0].sellIn).isEqualTo(-1);
        assertThat(items[1].sellIn).isEqualTo(-2);
        assertThat(items[2].sellIn).isEqualTo(-2);
        assertThat(items[3].sellIn).isEqualTo(-2);
    }
}
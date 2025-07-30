package com.gildedtros.app;

import com.gildedtros.domain.Item;
import com.gildedtros.domain.factory.ItemFactory;
import com.gildedtros.domain.validation.util.Outcome;
import com.gildedtros.usecase.GildedTros;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("AXXES CODE KATA - GILDED TROS");

        Outcome<Item>[] outcomes = new Outcome[]{
                ItemFactory.createItem("Ring of Cleansening Code", 10, 20),
                ItemFactory.createGoodWine("Good Wine", 2, 0),
                ItemFactory.createItem("Elixir of the SOLID", 5, 7),
                ItemFactory.createLegendaryItem("B-DAWG Keychain", 0),
                ItemFactory.createLegendaryItem("B-DAWG Keychain", -1),
                ItemFactory.createBackstagePass("Backstage passes for Re:Factor", 15, 20),
                ItemFactory.createBackstagePass("Backstage passes for Re:Factor", 10, 49),
                ItemFactory.createBackstagePass("Backstage passes for HAXX", 5, 49),
                ItemFactory.createSmellyItem("Duplicate Code", 3, 6),
                ItemFactory.createSmellyItem("Long Methods", 3, 6),
                ItemFactory.createSmellyItem("Ugly Variable Names", 3, 6)
        };

        Item[] items = new Item[outcomes.length];
        for (int i = 0; i < outcomes.length; i++) {
            Outcome<Item> outcome = outcomes[i];
            if (outcome instanceof Outcome.Success<Item> success) {
                items[i] = success.getValue();
            } else if (outcome instanceof Outcome.Failure<Item> failure) {
                // Handle failure case as needed, e.g., log message
                System.out.println("Failed to create item: " + failure.getMessage());
                items[i] = new Item("Error Placeholder", 0, 0); // Placeholder for visualization
            }
        }

        GildedTros app = new GildedTros(items);
        int days = 5;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }
        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }
}
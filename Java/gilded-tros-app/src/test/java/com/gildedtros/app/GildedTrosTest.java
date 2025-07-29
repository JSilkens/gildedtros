package com.gildedtros.app;

import com.gildedtros.domain.Item;
import com.gildedtros.usecase.GildedTros;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedTrosTest {

    @Test
    @Disabled
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

}

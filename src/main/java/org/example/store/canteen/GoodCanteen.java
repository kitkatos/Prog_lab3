package org.example.store.canteen;

import org.example.item.food.DeliciousMeet;
import org.example.item.food.Food;

public class GoodCanteen extends Canteen {
    public GoodCanteen() {
        super("Хорошая столовая", new Food[]{new Food("Каша", 15), new DeliciousMeet()}, new int[]{5, 15});
    }
}

package org.example.store.canteen;

import org.example.item.food.DeliciousMeet;
import org.example.item.food.Food;
import org.example.item.food.Poridge;

public class GoodCanteen extends Canteen {
    public GoodCanteen() {
        super("Хорошая столовая", new Food[]{new Poridge(), new DeliciousMeet()}, new int[]{5, 15});
    }
}

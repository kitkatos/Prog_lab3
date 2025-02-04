package org.example.store.canteen;

import org.example.item.food.Food;
import org.example.item.food.Poridge;
import org.example.item.food.RottenApple;

public class PoorCanteen extends Canteen {
    public PoorCanteen() {
        super("Бедная столовая", new Food[]{new Poridge(), new RottenApple()}, new int[]{10, 2});
    }
}

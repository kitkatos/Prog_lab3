package org.example.exception;

import org.example.item.food.Food;
import org.example.store.ItemPrice;
import org.example.store.canteen.Canteen;

public class ItemListLengthIsNotEqualToFoodListLength extends RuntimeException {
    public final Canteen canteen;
    public final int[] itemList;

    public ItemListLengthIsNotEqualToFoodListLength(Canteen canteen, int[] itemList) {
        super();
        this.canteen = canteen;
        this.itemList = itemList;
    }

    @Override
    public String getMessage() {
        return "itemList length is not equal to foodList length";
    }
}

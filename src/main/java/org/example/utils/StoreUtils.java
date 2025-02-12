package org.example.utils;

import org.example.store.ItemPrice;

public class StoreUtils {

    public static int countAvailableItems(ItemPrice[] items, int money) {
        int result = 0;

        for(var item : items) {
            if (item.price() <= money) {
                result++;
            }
        }

        return result;
    }

    public static boolean checkPrices(int[] prices) {
        for(var price : prices) {
            if (price < 0) {
                return false;
            }
        }

        return true;
    }

    public static ItemPrice[] getAccesableItems(ItemPrice[] items, int money) {
        ItemPrice[] accesableItems = new ItemPrice[countAvailableItems(items, money)];

        int i = 0;
        for(var item : items) {
            if (item.price() <= money) {
                accesableItems[i] = item;
                i++;
            }
        }

        return accesableItems;
    }

    public static int getItemPriceIndex(ItemPrice[] items, ItemPrice neededItem) {
        for(int i = 0; i < items.length; i++) {
            if (items[i].equals(neededItem)) {
                return i;
            }
        }

        throw new IllegalArgumentException("No such item: " + neededItem);
    }
}

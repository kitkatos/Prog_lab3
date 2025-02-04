package org.example.store.canteen;

import org.example.exception.ItemListLengthIsNotEqualToFoodListLength;
import org.example.exception.NotEnoughMoneyToBuyExcpetion;
import org.example.exception.UnacceptablePricesException;
import org.example.item.food.Food;
import org.example.person.Citizen;
import org.example.store.ItemPrice;
import org.example.store.StoreInterface;
import org.example.utils.StoreUtils;

public class Canteen implements StoreInterface {
    private final Food[] foodList;
    private ItemPrice[] pricesList;
    private final String name;

    public Canteen(String name, Food[] foodList, int[] foodPrices) {
        if(!StoreUtils.checkPrices(foodPrices)) {
            throw new UnacceptablePricesException(foodPrices);
        }

        if (foodList.length != foodPrices.length) {
            throw new ItemListLengthIsNotEqualToFoodListLength(this, foodPrices);
        }

        this.foodList = foodList;
        this.name = name;

        this.pricesList = fillPriceList(foodPrices);
    }

    private ItemPrice[] fillPriceList(int[] prices) {
        if(!StoreUtils.checkPrices(prices)) {
            throw new UnacceptablePricesException(prices);
        }

        var pricesList = new ItemPrice[prices.length];

        if(prices.length != this.foodList.length) {
            throw new ItemListLengthIsNotEqualToFoodListLength(this, prices);
        }

        for (int i = 0; i < foodList.length; i++) {
            String foodName = foodList[i].getName();
            int price = prices[i];
            pricesList[i] = new ItemPrice(foodName, price);
        }

        return pricesList;
    }

    @Override
    public ItemPrice[] getPriceList() {
        return this.pricesList;
    }

    @Override
    public ItemPrice[] getAccessableItems(int money) {
        return StoreUtils.getAccesableItems(this.pricesList, money);
    }

    @Override
    public Food buy(ItemPrice itemToBuy, Citizen buyer) {
        if(buyer.getMoney() < itemToBuy.price()) {
            throw new NotEnoughMoneyToBuyExcpetion(buyer, itemToBuy);
        }

        buyer.buyItem(itemToBuy);

        int indexOfFood = StoreUtils.getItemPriceIndex(this.pricesList, itemToBuy);
        return this.foodList[indexOfFood];
    }

    @Override
    public String toString() {
        String output = "Столовая: " + this.name + "\n";
        for(var price : this.pricesList) {
            output += price.toString() + "\n";
        }

        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Canteen other)) {
            return false;
        }

        boolean result = this.name.equals(other.name);

        for(int i = 0; i < this.foodList.length; i++) {
            result = result & this.foodList[i].equals(other.foodList[i]);
            result = result & this.pricesList[i].equals(other.pricesList[i]);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hashCode = this.name.hashCode();

        for(int i = 0; i < this.foodList.length; i++) {
            hashCode = hashCode | this.foodList[i].hashCode();
            hashCode = hashCode | this.pricesList[i].hashCode();
        }

        return hashCode;
    }
}

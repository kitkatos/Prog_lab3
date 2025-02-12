package org.example.store;

import org.example.item.Item;
import org.example.person.Citizen;

public interface StoreInterface {
    public ItemPrice[] getPriceList();
    public ItemPrice[] getAccessableItems(int money);
    public Item buy(ItemPrice itemToBuy, Citizen buyer);
}

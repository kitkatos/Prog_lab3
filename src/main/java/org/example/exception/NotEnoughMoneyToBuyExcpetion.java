package org.example.exception;

import org.example.person.Citizen;
import org.example.store.ItemPrice;

public class NotEnoughMoneyToBuyExcpetion extends RuntimeException {
    public final Citizen buyer;
    public final ItemPrice item;

    public NotEnoughMoneyToBuyExcpetion(Citizen buyer, ItemPrice item) {
        super("Недостаточно денег чтобы купить товар");
        this.buyer = buyer;
        this.item = item;
    }
}

package org.example.person;

import org.example.exception.ActivateDeadManExcpetion;
import org.example.exception.TooManyMoneyToExtractExcpetion;
import org.example.item.Item;
import org.example.newspaper.Newspaper;
import org.example.newspaper.NewspaperReader;
import org.example.store.ItemPrice;

import java.util.Random;

public class Citizen extends Human implements MoneyHolder, NewspaperReader {
    private final int id;
    private int money;

    public Citizen(String name, int age) {
        super(name, age);

        Random rand = new Random();
        this.id = rand.nextInt(10000);

        this.money = 0;
    }

    public Citizen(String name, int age, int money) {
        this(name, age);

        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }

        this.money = money;
    }

    public Citizen(Human person) {
        this(person.getName(), person.getAge());
    }

    public Citizen(Human person, int money) {
        this(person.getName(), person.getAge(), money);
    }

    public final int getId() {
        return id;
    }

    public final int getMoney() {
        return money;
    }

    public void reciveMoney(int amount) {
        if(this.getPhysicalState() == PhysicalState.Dead) {
            throw new ActivateDeadManExcpetion("Мертвый человек не может получать деньги");
        }

        this.money += amount;
    }

    public final void extractMoney(int amount) {
        if (money < amount) {
            throw new TooManyMoneyToExtractExcpetion(this.getName(), amount);
        }

        money -= amount;
    }

    public void buyItem(ItemPrice itemToBuy) {
        if(this.getPhysicalState() == PhysicalState.Dead) {
            throw new ActivateDeadManExcpetion("Мертвый человек покупать вещи");
        }

        this.extractMoney(itemToBuy.price());

        System.out.printf("%s покупает %s за %d\n", this.getName(), itemToBuy.name(), itemToBuy.price());
        System.out.printf("Баланс: %d\n", this.getMoney());
    }

    @Override
    public void readNewspaper(Newspaper newspaper) {
        System.out.printf("Гражданин %s читает новую газету\n", this.getName());
    }

    @Override
    public String toString() {
        return String.format("Гражданин %s: id: %d, деньги: %d, возраст: %d, хп: %d, голод: %d" , super.getName(), id, money, super.getAge(), super.getHp(), super.getHunger());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        if (!(obj instanceof Citizen other)) {
            return false;
        }

        return super.equals(obj) && other.getId() == this.getId() && other.getMoney() == this.getMoney();
    }

    @Override
    public int hashCode() {
        return super.hashCode() | this.getId() | this.getMoney();
    }
}

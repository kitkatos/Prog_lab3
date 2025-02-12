package org.example.item.food;

import org.example.person.Human;
import org.example.person.MentalState;

public class DeliciousMeet extends Food {
    public DeliciousMeet() {
        super("Изысканное мясо", 30);
    }

    @Override
    public void beEaten(Human human) {
        super.beEaten(human);
        human.setMentalState(MentalState.Upbeat);
        System.out.printf("%s оказалось очень вкусным. Настроение %s поднялось\n", getName(), human.getName());
    }
}

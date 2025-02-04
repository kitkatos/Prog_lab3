package org.example.item.food;

import org.example.person.Human;
import org.example.person.MentalState;

public class RottenApple extends Food {
    public RottenApple() {
        super("Гнилое яблоко", 5);
    }

    @Override
    public void beEaten(Human human) {
        super.beEaten(human);
        human.getDamage(10, MentalState.Depressed);
        System.out.printf("Кажется это яблоко не первой свежести... %s получает урон и расстраивается\n", human.getName());
    }
}

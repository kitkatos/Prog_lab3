package org.example.item.food;

import org.example.item.Item;
import org.example.person.Human;

public class Food extends Item implements Eatable {
    private final int hunger;

    public Food(String name, int hunger) {
        super(name);

        this.hunger = hunger;
    }

    public int getHunger() {
        return hunger;
    }

    public void beEaten(Human human) {
        human.increaseHunger(hunger);
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getName(), getHunger());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if(obj == this) {
            return true;
        }

        if(!(obj instanceof Food other)) {
            return false;
        }

        return super.equals(obj) && getHunger() == other.getHunger();
    }

    @Override
    public int hashCode() {
        return super.hashCode() | this.getHunger();
    }
}

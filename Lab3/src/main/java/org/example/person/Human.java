package org.example.person;

import org.example.item.food.Food;

public class Human {
    private final String name;
    private int age;
    private int hp;
    private int hunger;
    private MentalState mentalState;
    private PhysicalState physicalState;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.hp = 100;
        this.hunger = 100;
        this.mentalState = MentalState.Well;
        this.physicalState = PhysicalState.Well;
    }

    protected final void setHP(int hp) {
        if (hp < 0 || hp > 100) {
            throw new IllegalArgumentException("Hp must be between 0 and 100");
        }

        this.hp = hp;

        if(this.hp == 0) {
            this.physicalState = PhysicalState.Dead;
        }
    }

    public final int getHp() {
        return this.hp;
    }

    public void getDamage(int damage) {
        if(damage > this.hp) {
            damage = this.hp;
        }

        this.setHP(this.hp - damage);
    }

    public void getDamage(int damage, MentalState mentalState) {
        this.getDamage(damage);
        this.setMentalState(mentalState);
    }

    public void getDamage(int damage, MentalState mentalState, PhysicalState physicalState) {
        this.getDamage(damage);
        this.setMentalState(mentalState);

        if(physicalState != PhysicalState.Dead) {
            this.setPhysicalState(physicalState);
        }
    }

    public void eat(Food food) {
        System.out.printf("%s кушает %s\n", this.name, food.getName());
        food.beEaten(this);
        System.out.println(this);
    }

    public void increaseHunger(int saturation) {
        if (saturation + this.hunger < 0) {
            this.hunger = 0;
        }
        else if (saturation + this.hunger > 100) {
            this.hunger = 100;
        }
        else {
            this.hunger += saturation;
        }
    }

    public void setMentalState(MentalState mentalState) {
        this.mentalState = mentalState;
    }

    public void setPhysicalState(PhysicalState physicalState) {
        this.physicalState = physicalState;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Age must be between 0 and 100");
        }

        this.age = age;
    }

    public final void setHunger(int hunger) {
        if (hunger < 0 || hunger > 100) {
            throw new IllegalArgumentException("Hunger must be between 0 and 100");
        }

        this.hunger = hunger;
    }

    public int getHunger() {
        return this.hunger;
    }

    public int getAge() {
        return this.age;
    }

    public MentalState getMentalState() {
        return mentalState;
    }

    public PhysicalState getPhysicalState() {
        return physicalState;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Human name: %s, age: %d, hp: %d, hunger: %d", name, age, hp, hunger);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Human other)) {
            return false;
        }

        return other.age == this.age &&
                other.hp == this.hp &&
                other.hunger == this.hunger &&
                other.name.equals(this.name) &&
                other.mentalState.ordinal() == this.mentalState.ordinal() &&
                other.physicalState.ordinal() == this.physicalState.ordinal();
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() | this.age | this.hp;
    }
}

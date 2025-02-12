package org.example.person.worker;

import org.example.person.Citizen;
import org.example.person.MentalState;

public abstract class Worker extends Citizen {
    private int daysWithoutPaying;

    public Worker(String name, int age) {
        super(name, age);

        daysWithoutPaying = 0;
    }

    public Worker(String name, int age, int money) {
        super(name, age, money);

        daysWithoutPaying = 0;
    }

    public abstract int produce();

    public abstract void restAtWeekend();

    protected void setDaysWithoutPaying(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("days must be a positive integer");
        }

        daysWithoutPaying = days;
    }

    public void getPaid(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("money must be a positive integer");
        }

        if (money == 0) {
            this.increaseDaysWithoutPaying();
            return;
        }

        this.reciveMoney(money);
        this.setDaysWithoutPaying(0);
        System.out.printf("Работник %s получает зарплату %d\n", this.getName(), money);
    }

    public void getBonus(int bonus) {
        this.reciveMoney(bonus);
        this.setMentalState(MentalState.Upbeat);

        System.out.printf("Работник %s получает бонус %d. Он радуется!!\n", this.getName(), bonus);
    }

    public int getDaysWithoutPaying() {
        return this.daysWithoutPaying;
    }

    public void increaseDaysWithoutPaying() {
        this.daysWithoutPaying++;

        System.out.printf("Работник %s сегодня остается без зарплаты\n", this.getName());
    }

    @Override
    public String toString() {
        return String.format("Работник %s: id: %d, деньги: %d, возраст: %d, хп: %d, голод: %d" , super.getName(), super.getId(), super.getMoney(), super.getAge(), super.getHp(), super.getHunger());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Worker other)) {
            return false;
        }

        return super.equals(obj) && other.getDaysWithoutPaying() == this.getDaysWithoutPaying();
    }

    @Override
    public int hashCode() {
        return super.hashCode() | this.getDaysWithoutPaying();
    }
}

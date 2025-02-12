package org.example.person.worker;

import org.example.exception.ActivateDeadManExcpetion;
import org.example.person.MentalState;
import org.example.person.PhysicalState;

import java.util.Random;

public class NormalWorker extends Worker {

    public NormalWorker(String name, int age) {
        super(name, age);
    }

    public NormalWorker(String name, int age, int money) {
        super(name, age, money);
    }

    @Override
    public int produce() {
        if(this.getPhysicalState() == PhysicalState.Dead) {
            throw new ActivateDeadManExcpetion("Нельзя заставить работать мертвого сотрудника");
        }

        double mentalModificator = 0;
        switch (this.getMentalState()) {
            case Upbeat -> mentalModificator = 3;
            case Depressed -> mentalModificator = 0.0;
            case Well -> mentalModificator = 1;
            case Angry -> mentalModificator = 0.1;
        }

        Random rnd = new Random();

        this.setHunger(this.getHunger() - 15);
        return (int) Math.round(mentalModificator * (12 + rnd.nextInt(-5, 9)));
    }

    @Override
    public void restAtWeekend() {
        System.out.printf("Работник %s отлично отдохнул и очень хочет вернуться к работе!\n", this.getName());

        this.setMentalState(MentalState.Upbeat);
    }

    @Override
    public void increaseDaysWithoutPaying() {
        super.increaseDaysWithoutPaying();

        if(this.getDaysWithoutPaying() <= 4) {
            return;
        }
        System.out.printf("Работник %s долго не получает зарплату, здоровье снижено и испорчено настроение\n", getName());
        this.getDamage(2, MentalState.Depressed);
        System.out.println(this);
    }
}

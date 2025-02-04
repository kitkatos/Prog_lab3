package org.example.person.worker;

import org.example.exception.ActivateDeadManExcpetion;
import org.example.exception.UncasedMentalStateExcpetion;
import org.example.person.MentalState;
import org.example.person.PhysicalState;

public class HardworkingWorker extends Worker {
    public HardworkingWorker(String name, int age) {
        super(name, age);
    }

    public HardworkingWorker(String name, int age, int money) {
        super(name, age, money);
    }

    @Override
    public int produce() {
        if(this.getPhysicalState() == PhysicalState.Dead) {
            throw new ActivateDeadManExcpetion("Нельзя заставить работать мертвого сотрудника");
        }

        this.setHunger(this.getHunger() - 15);
        switch (this.getMentalState()) {
            case Angry -> {
                return 10;
            }
            case Depressed -> {
                return 20;
            }
            case Well -> {
                return 30;
            }
            case Upbeat -> {
                return 50;
            }
            default -> {
                throw new UncasedMentalStateExcpetion();
            }
        }
    }

    @Override
    public void restAtWeekend() {
        System.out.printf("Работник %s отлично отдохнул и очень хочет вернуться к работе!\n", this.getName());

        this.setMentalState(MentalState.Upbeat);
    }

    @Override
    public void increaseDaysWithoutPaying() {
        super.increaseDaysWithoutPaying();

        if(this.getDaysWithoutPaying() <= 2) {
            return;
        }
        System.out.printf("Работник %s долго не получает зарплату, здоровье снижено и испорчено настроение\n", getName());
        this.getDamage(2, MentalState.Depressed);
        System.out.println(this);
    }
}

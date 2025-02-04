package org.example.factory;

import org.example.exception.NoEmptySlotException;
import org.example.person.PhysicalState;
import org.example.person.buisnessman.BusinessMan;
import org.example.person.worker.Worker;
import org.example.store.canteen.Canteen;

import java.util.Random;

public abstract class Factory {
    protected final WorkerInformation[] workersInformations;
    protected final BusinessMan owner;
    protected final String name;
    protected final Canteen canteen;
    protected int workingDays;

    public Factory(String name, int MaxWorkerCount, BusinessMan owner, Canteen canteen) {
        this.name = name;
        this.workersInformations = new WorkerInformation[MaxWorkerCount];
        this.owner = owner;
        this.canteen = canteen;
        this.workingDays = 0;
    }

    public Factory(String name, WorkerInformation[] workersInformations, BusinessMan owner, Canteen canteen) {
        this.name = name;
        this.workersInformations = workersInformations;
        this.owner = owner;
        this.canteen = canteen;
        this.workingDays = 0;
    }

    protected int getEmptySlot() {
        for(int i = 0; i < workersInformations.length; i++) {
            if(workersInformations[i] == null) {
                return i;
            }
        }

        return -1;
    }

    public void addNewWorker(Worker worker, int salary) {
        int slot = getEmptySlot();

        if (slot == -1) {
            throw new NoEmptySlotException();
        }

        this.workersInformations[slot] = new WorkerInformation(worker, salary, 3);
    }

    public WorkerInformation[] getWorkersInformation() {
        return this.workersInformations;
    }

    public BusinessMan getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public Canteen getCanteen() {
        return this.canteen;
    }

    public int getLastDays() {
        return this.workingDays;
    }

    protected void increaseWorkingDays() {
        this.workingDays++;
    }

    public void workADay() {
        this.increaseWorkingDays();
        System.out.printf("Фабрика %s начинает свой новый день: %d\n", this.name, this.workingDays);
        this.produce();
        this.lunch();
        this.timeForBreak();

        var report = this.formReport();
        this.reportToOwnerAboutTheDay(report);

        this.giveSalaryToWorkers();

        System.out.println("Рабочий день закончен на этой фабрике!!\n\n");
    }

    protected abstract void produce();

    protected void lunch() {
        for (var workerInfo : workersInformations) {
            if (workerInfo == null || workerInfo.getWorker().getPhysicalState() == PhysicalState.Dead) {
                continue;
            }

            var worker = workerInfo.getWorker();

            if(this.workingDays % workerInfo.getWeekendInterval() == 0) {
                continue;
            }

            System.out.printf("\nРаботник %s обедает в столовой\n", worker.getName());

            var availableFoodToBuy = this.canteen.getAccessableItems(worker.getMoney());

            if(availableFoodToBuy.length == 0) {
                System.out.printf("\nРаботник %s ничего не может купить поесть :(\n", worker.getName());
                continue;
            }

            Random rnd = new Random();

            var foodToBuy = availableFoodToBuy[rnd.nextInt(0, availableFoodToBuy.length)];
            System.out.printf("\nРаботник %s покупает себе %s за %d\n", worker.getName(), foodToBuy.name(), foodToBuy.price());

            var food = this.canteen.buy(foodToBuy, worker);
            worker.eat(food);
        }
    }

    protected abstract void timeForBreak();

    protected void reportToOwnerAboutTheDay(FactoryReport report) {
        System.out.println("Конец рабочего дня! Владелец анализирует текущий день");

        this.owner.analyzeReport(report);
    }

    protected void giveSalaryToWorkers() {
        System.out.println("Время зарплаты!");

        this.owner.paySalaryToAllWorkers(this.workersInformations);
    }

    protected abstract int calculateProfit();

    protected FactoryReport formReport() {
        int amountOfProduced = 0;

        for(var worker : this.workersInformations) {
            if(worker != null) {
                amountOfProduced += worker.getAmountOfProduceToday();
            }
        }

        int profit = this.calculateProfit();

        return new FactoryReport(this, amountOfProduced, profit, this.workersInformations);
    }

    @Override
    public String toString() {
        String output = "Фабрика " + this.name + "\n";
        output += "Владелец: " + this.owner.toString() + "\n";
        output += "Работники: \n";
        for(var worker : this.workersInformations) {
            if(worker != null) {
                output += worker.toString() + "\n";
            }
        }

        output += "Столовая: " + this.canteen.toString() + "\n";

        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Factory other)) {
            return false;
        }

        boolean result = this.name.equals(other.name);
        result = result && this.owner.equals(other.owner);

        for (int i = 0; i < this.workersInformations.length; i++) {
            result = result && this.workersInformations[i].equals(other.workersInformations[i]);
        }

        result = result && this.canteen.equals(other.canteen);

        return result;
    }

    @Override
    public int hashCode() {
        int hashCode = this.name.hashCode();
        hashCode = hashCode | this.owner.hashCode();

        for (WorkerInformation workerInformation : this.workersInformations) {
            if (workerInformation != null) {
                hashCode = hashCode | workerInformation.hashCode();
            }
        }

        hashCode = hashCode | this.canteen.hashCode();

        return hashCode;
    }
}

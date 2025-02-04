package org.example.factory;

import org.example.person.worker.Worker;

public class WorkerInformation {
    private final Worker worker;
    private int salary;
    private int amountOfProduceToday;
    private int weekendInterval;

    public WorkerInformation(Worker worker, int salary, int weekendInterval) {
        this.worker = worker;
        this.salary = salary;
        this.amountOfProduceToday = 0;
        this.weekendInterval = weekendInterval;
    }

    public Worker getWorker() {
        return worker;
    }

    public int getSalary() {
        return salary;
    }

    public void setAmountOfProduceToday(int amountOfProduceToday) {
        if (amountOfProduceToday < 0) {
            throw new IllegalArgumentException("Amount of produce today must be >= 0");
        }

        this.amountOfProduceToday = amountOfProduceToday;
    }

    public int getAmountOfProduceToday() {
        return amountOfProduceToday;
    }

    public int getWeekendInterval() {
        return weekendInterval;
    }

    public void setSalary(int salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }

        this.salary = salary;
    }

    public void updateAmountOfProduce(int amountOfProduce) {
        if (amountOfProduce < 0) {
            throw new IllegalArgumentException("AmountOfProduce cannot be negative");
        }

        this.amountOfProduceToday += amountOfProduce;
    }

    public void setWeekendInterval(int weekendInterval) {
        if(weekendInterval < 0) {
            throw new IllegalArgumentException("WeekendInterval cannot be negative");
        }

        this.weekendInterval = weekendInterval;
    }

    public static int SummarizeAllSalaries(WorkerInformation[] workers) {
        int sum = 0;

        for (var worker : workers) {
            if(worker == null) {
                continue;
            }

            sum += worker.getSalary();
        }

        return sum;
    }
}

package org.example.person.buisnessman;

import org.example.exception.ActivateDeadManExcpetion;
import org.example.exception.CannotPaySalaryException;
import org.example.factory.FactoryReport;
import org.example.factory.WorkerInformation;
import org.example.person.Citizen;
import org.example.person.PhysicalState;
import org.example.stock.StockPortfolio;

public abstract class BusinessMan extends Citizen {
    private StockPortfolio portfolio;

    public BusinessMan(String name, int age) {
        super(name, age);
        this.portfolio = new StockPortfolio(20);
    }

    public BusinessMan(String name, int age, int money) {
        super(name, age, money);
        this.portfolio = new StockPortfolio(20);
    }

    public StockPortfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(StockPortfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void getProfit(int profit) {
        if (profit < 0) {
            System.out.println("The profit cannot be negative");
        }

        super.reciveMoney(profit);
        System.out.printf("Бизнесмен %s получает доход %d рублей\n", this.getName(), profit);
    }

    public void setWorkerSalary(WorkerInformation worker, int newSalary) {
        if (newSalary < 0) {
            throw new IllegalArgumentException("New salary cannot be negative");
        }

        worker.setSalary(newSalary);

        System.out.printf("Бизнесмен %s установил для работинка %s новую зарплату: %d\n", this.getName(), worker.getWorker().getName(), newSalary);
    }

    public boolean canPayAllSalary(WorkerInformation[] workers) {
        int allSalary = WorkerInformation.SummarizeAllSalaries(workers);

        return allSalary < this.getMoney();
    }

    public void paySalaryToWorker(WorkerInformation workerInfo) {
        int salary = workerInfo.getSalary();
        var worker = workerInfo.getWorker();

        if(workerInfo.getSalary() > this.getMoney()) {
            String message = String.format("Бизнесмен %s не может выплатить зарплату %d работнику %s, потому что у бизнесмена нет денег\n", this.getName(), salary, worker.getName());
            throw new CannotPaySalaryException(message);
        }

        worker.getPaid(salary);
        this.extractMoney(salary);

        System.out.printf("Бизнесмен %s выплачивает зарплату %d работнику %s\n", this.getName(), salary, worker.getName());
    }

    public void paySalaryToAllWorkers(WorkerInformation[] workers) {
        int allSalary = WorkerInformation.SummarizeAllSalaries(workers);

        if(allSalary > this.getMoney()) {
            System.out.printf("Бизнесмен %s не может выплатить зарплаты своим работникам, потому что у бизнесмена нет денег\n", this.getName());
            for(var worker : workers) {
                if(worker == null || worker.getWorker().getPhysicalState() == PhysicalState.Dead) {
                    continue;
                }
                worker.getWorker().increaseDaysWithoutPaying();
            }

            return;
        }

        for(var worker : workers) {
            if(worker == null || worker.getWorker().getPhysicalState() == PhysicalState.Dead) {
                continue;
            }
            paySalaryToWorker(worker);
        }

        System.out.printf("Капитал бизенсемна %s после выдачи зарплат: %d\n", this.getName(), this.getMoney());
    }

    public void payBonusToWorker(WorkerInformation workerInfo, int bonus) {
        if(bonus <= 0) {
            throw new IllegalArgumentException("Bonus must be positive");
        }

        if(bonus > this.getMoney()) {
            throw new CannotPaySalaryException("Недостаточно денег для бонуса");
        }

        var worker = workerInfo.getWorker();

        if(worker.getPhysicalState() == PhysicalState.Dead) {
            throw new ActivateDeadManExcpetion("Нельзя выдать бонус мертвому человеку");
        }

        worker.getBonus(bonus);
        this.extractMoney(bonus);

        System.out.printf("Бизнесмен %s выплачивает бонус %d работнику %s\n", this.getName(), bonus, worker.getName());
    }

    public void analyzeReport(FactoryReport factoryReport) {
        this.getProfit(factoryReport.profit());

        System.out.printf("Бизнесмен %s заработал сегодня %d\n", this.getName(), factoryReport.profit());
        System.out.printf("Капитал бизнесмена %s: %d\n", this.getName(), this.getMoney());
    }

    @Override
    public String toString() {
        return String.format("Бизнесмен %s: id: %d, деньги: %d, возраст: %d, хп: %d, голод: %d" , super.getName(), super.getId(), super.getMoney(), super.getAge(), super.getHp(), super.getHunger());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        if(!(obj instanceof BusinessMan)) {
            return false;
        }

        return super.equals(obj);
    }
}

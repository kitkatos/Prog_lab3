package org.example.person.buisnessman;

import org.example.factory.FactoryReport;
import org.example.person.PhysicalState;

public class EvilBusinessMan extends BusinessMan {
    public EvilBusinessMan(String name, int age) {
        super(name, age);
    }

    public EvilBusinessMan(String name, int age, int money) {
        super(name, age, money);
    }

    @Override
    public void analyzeReport(FactoryReport factoryReport) {
        super.analyzeReport(factoryReport);

        if(factoryReport.profit() >= 500) {
            System.out.printf("Бизнесмен %s доволен продажами, поэтому никому зарплату не понижает\n", this.getName());
            return;
        }

        System.out.printf("Бизнесмен %s не доволен продажами, поэтому понижает всем зарплату на 1\n", this.getName());

        for(var workerInfo : factoryReport.workers()) {
            if(workerInfo == null || workerInfo.getWorker().getPhysicalState() == PhysicalState.Dead) {
                continue;
            }

            int salary = workerInfo.getSalary();
            if(salary == 0) {
                continue;
            }

            salary -= 1;

            workerInfo.setSalary(salary);
        }
    }
}

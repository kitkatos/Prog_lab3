package org.example.person.buisnessman;

import org.example.factory.FactoryReport;

public class GoodBusinessman extends BusinessMan {
    public GoodBusinessman(String name, int age) {
        super(name, age);
    }

    public GoodBusinessman(String name, int age, int money) {
        super(name, age, money);
    }

    @Override
    public void analyzeReport(FactoryReport factoryReport) {
        super.analyzeReport(factoryReport);

        System.out.printf("Бизнесмен %s доволен сегодняшним днем!!\n", this.getName());
    }
}

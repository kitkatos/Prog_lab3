package org.example;

import org.example.factory.PoorFactory;
import org.example.factory.RichFactory;
import org.example.person.buisnessman.BusinessMan;
import org.example.person.buisnessman.EvilBusinessMan;
import org.example.person.buisnessman.GoodBusinessman;
import org.example.person.worker.HardworkingWorker;
import org.example.person.worker.NormalWorker;

public class Main {
    public static void main(String[] args) {
        GoodBusinessman GoodBigMan = new GoodBusinessman("Гаврилов", 34, 10000);
        EvilBusinessMan EvilBigMan = new EvilBusinessMan("Господин К.", 45, 10000);

        HardworkingWorker worker1 = new HardworkingWorker("Леня", 23, 100);
        HardworkingWorker worker2 = new HardworkingWorker("Леша", 25, 100);

        NormalWorker worker3 = new NormalWorker("Саня", 21, 20);
        NormalWorker worker4 = new NormalWorker("Олег", 24, 45);

        RichFactory richFactory = new RichFactory("Мегакрутая фабрика 1000 уровня", 2, GoodBigMan);
        PoorFactory poorFactory = new PoorFactory("Бедный завод :(", 2, EvilBigMan);

        richFactory.addNewWorker(worker1, 100);
        richFactory.addNewWorker(worker4, 75);

        poorFactory.addNewWorker(worker2, 50);
        poorFactory.addNewWorker(worker3, 10);

        for(int i = 0; i < 7; i++) {
            richFactory.workADay();
            poorFactory.workADay();
        }
    }
}
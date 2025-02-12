package org.example;

import org.example.factory.PoorFactory;
import org.example.factory.RichFactory;
import org.example.newspaper.NewspaperPublisher;
import org.example.person.MentalState;
import org.example.person.buisnessman.EvilBusinessMan;
import org.example.person.buisnessman.GoodBusinessman;
import org.example.person.worker.HardworkingWorker;
import org.example.person.worker.NormalWorker;
import org.example.stock.StockBroker;
import org.example.stock.StockMarket;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        GoodBusinessman GoodBigMan = new GoodBusinessman("Гаврилов", 34, 10000);
        EvilBusinessMan Skuperfild = new EvilBusinessMan("Скуперфилд", 45, 500);

        HardworkingWorker worker1 = new HardworkingWorker("Леня", 23, 100);
        HardworkingWorker worker2 = new HardworkingWorker("Леша", 25, 100);

        NormalWorker worker3 = new NormalWorker("Саня", 21, 20);
        NormalWorker worker4 = new NormalWorker("Олег", 24, 45);

        StockMarket stockMarket = new StockMarket(20);
        stockMarket.registerStock("Фантики", 100);
        Skuperfild.getPortfolio().addStock("Фантики", 100);

        StockBroker stockBroker = new StockBroker(stockMarket);

        NewspaperPublisher publisher = new NewspaperPublisher("Чистая правда", 25, 20);

        RichFactory richFactory = new RichFactory("Мегакрутая фабрика 1000 уровня", 2, GoodBigMan);
        PoorFactory poorFactory = new PoorFactory("Бедный завод :(", 2, Skuperfild);

        richFactory.addNewWorker(worker1, 100);
        richFactory.addNewWorker(worker4, 75);

        poorFactory.addNewWorker(worker2, 50);
        poorFactory.addNewWorker(worker3, 10);

        //День первый
        richFactory.workADay();
        poorFactory.workADay();

        publisher.publishNewspaper("СРОЧНЫЕ НОВОСТИ: Миги и Жулио совершили побег! Полиция уже ведет поиски", 1);

        System.out.println("");

        richFactory.workADay();
        poorFactory.workADay();

        stockMarket.setStockPrice("Фантики", 1);

        publisher.publishNewspaper("СРОЧНЫЕ НОВОСТИ: ОБВАЛ НА ФОНДОВОМ РЫНКЕ! После побега Миги и Жулио цена на акции Фантиков резко полетели вниз - за стуки стоимость упала в рекордные 100 раз", 1);
        System.out.printf("%s продает все свои акции\n", Skuperfild.getName());
        stockBroker.sellStock(Skuperfild, Skuperfild.getPortfolio(), "Фантики", 100);
        System.out.println(Skuperfild.getName() + "\n");


        Skuperfild.setMentalState(MentalState.Depressed);
        System.out.printf("%s разочарован из-за падения акций, он на мели. Он решает урезать зарплату\n", Skuperfild.getName());



        System.out.println("");


        for(var workerInfo : poorFactory.getWorkersInformation()) {
            Skuperfild.setWorkerSalary(workerInfo, workerInfo.getSalary() / 2);
        }


        for(int i = 0; i < 3; i++) {
            richFactory.workADay();
            poorFactory.workADay();
        }

        System.out.printf("Работник %s не выдерживает и увольняется\n", worker2.getName());
        poorFactory.getFired(worker2);

        Random rnd = new Random();
        for(int i = 0; i < 3; i++) {
            richFactory.workADay();
            poorFactory.workADay();

            if(worker1.getMoney() > 50 && rnd.nextBoolean()) {
                System.out.printf("%s помогает %s: он отдает 50 монет\n", worker1.getName(), worker3.getName());
                worker1.extractMoney(50);
                worker3.reciveMoney(50);
            }
        }
    }
}
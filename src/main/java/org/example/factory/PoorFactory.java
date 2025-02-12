package org.example.factory;

import org.example.person.MentalState;
import org.example.person.PhysicalState;
import org.example.person.buisnessman.BusinessMan;
import org.example.store.canteen.Canteen;
import org.example.store.canteen.PoorCanteen;

import java.util.Random;

public class PoorFactory extends Factory {
    public PoorFactory(String name, int MaxWorkerCount, BusinessMan owner) {
        super(name, MaxWorkerCount, owner, new PoorCanteen());
    }

    public PoorFactory(String name, WorkerInformation[] workersInformations, BusinessMan owner) {
        super(name, workersInformations, owner, new PoorCanteen());
    }

    @Override
    protected void produce() {
        for (var workerInfo : workersInformations) {
            if (workerInfo == null || workerInfo.getWorker().getPhysicalState() == PhysicalState.Dead) {
                continue;
            }

            var worker = workerInfo.getWorker();

            if(this.workingDays % workerInfo.getWeekendInterval() == 0) {
                System.out.printf("Работник %s сегодня отдыхает\n", worker.getName());
                worker.restAtWeekend();
                System.out.println(worker);
            }

            System.out.printf("Работник %s работает\n", worker.getName());

            int produced = worker.produce();
            Random rnd = new Random();

            if (rnd.nextBoolean()) {
                System.out.printf("Работник %s прекрасно поработал! Произведено: %d\n", worker.getName(), produced);
                worker.setMentalState(MentalState.Well);
            }
            else {
                produced = 0;
                System.out.printf("О нет! Из-за плохого оборудования все детали пришли в негодность :(\nРаботник %s произвел сегодня 0\n", worker.getName());
                worker.setMentalState(MentalState.Angry);
            }

            workerInfo.setAmountOfProduceToday(produced);
        }
    }

    @Override
    protected void timeForBreak() {
        return;
    }

    @Override
    protected int calculateProfit() {
        int profit = 0;

        for(var workerInfo : this.workersInformations) {
            if(workerInfo == null) {
                continue;
            }

            profit += workerInfo.getAmountOfProduceToday() * 10;
        }

        return profit;
    }
}

package org.example.factory;

import org.example.person.MentalState;
import org.example.person.PhysicalState;
import org.example.person.buisnessman.BusinessMan;
import org.example.store.canteen.Canteen;
import org.example.store.canteen.GoodCanteen;

import java.util.Random;

public class RichFactory extends Factory {

    public RichFactory(String name, int MaxWorkerCount, BusinessMan owner) {
        super(name, MaxWorkerCount, owner, new GoodCanteen());
    }

    public RichFactory(String name, WorkerInformation[] workersInformations, BusinessMan owner) {
        super(name, workersInformations, owner, new GoodCanteen());
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

            System.out.printf("Работник %s работает, благодаря красивому интерьеру у него поднимается настроение\n", worker.getName());
            worker.setMentalState(MentalState.Well);

            int produced = worker.produce();

            workerInfo.setAmountOfProduceToday(produced);

            System.out.printf("Работник %s прекрасно поработал! Произведено: %d\n", worker.getName(), produced);
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
            profit += workerInfo.getAmountOfProduceToday() * 50;
        }

        return profit;
    }
}

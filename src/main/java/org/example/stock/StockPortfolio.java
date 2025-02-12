package org.example.stock;

import org.example.exception.NoEmptySlotException;
import org.example.exception.NotFoundElementInArrayException;
import org.example.utils.ArrayUtils;

public class StockPortfolio {
    private final Stock[] stocks;

    public StockPortfolio(int maxStockCount) {
        this.stocks = new Stock[maxStockCount];
    }

    public void addStock(String stockName, int count) {
        Stock stock = new Stock(stockName, count);
        addStock(stock);
    }

    public void addStock(Stock stock) {
        int stockIndex = findIndexOfStock(stock.getName());

        if(stockIndex == -1) {
            stockIndex = ArrayUtils.getFirstEmptySlot(this.stocks);

            if(stockIndex == -1) {
                throw new NoEmptySlotException();
            }

            this.stocks[stockIndex] = new Stock(stock.getName(), 0);
        }
        this.stocks[stockIndex].addStocks(stock.getCount());
    }

    public void removeStock(String stockName, int count) {
        int stockIndex = findIndexOfStock(stockName);

        if(stockIndex == -1) {
            throw new NotFoundElementInArrayException();
        }

        var stock = this.stocks[stockIndex];
        stock.removeStocks(count);

        if(stock.getCount() == 0) {
            this.stocks[stockIndex] = null;
        }
    }

    protected int findIndexOfStock(String stockName) {
        for (int i = 0; i < this.stocks.length; i++) {
            if (this.stocks[i] == null) {
                continue;
            }

            if (this.stocks[i].getName().equals(stockName)) {
                return i;
            }
        }

        return -1;
    }
}

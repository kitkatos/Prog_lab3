package org.example.stock;

import org.example.exception.NoEmptySlotException;
import org.example.utils.ArrayUtils;

public class StockMarket {
    private final String[] stockNames;
    private final int[] stockPrices;

    public StockMarket(int maxStocksCount) {
        this.stockNames = new String[maxStocksCount];
        this.stockPrices = new int[maxStocksCount];
    }

    private int findStock(String stockName) {
        for (int i = 0; i < this.stockNames.length; i++) {
            if(this.stockNames[i] == null) {
                continue;
            }

            if (this.stockNames[i].equals(stockName)) {
                return i;
            }
        }

        return -1;
    }

    public void registerStock(String stockName, int stockPrice) {
        int index = findStock(stockName);

        if (index == -1) {
            index = ArrayUtils.getFirstEmptySlot(this.stockNames);

            if(index == -1) {
                throw new NoEmptySlotException();
            }

            this.stockNames[index] = stockName;
        }

        this.stockPrices[index] = stockPrice;
    }

    public int getStockPrice(String name) {
        int index = findStock(name);

        if(index == -1) {
            throw new IllegalArgumentException("Нет такого зарегестрированной акции: " + name);
        }

        return this.stockPrices[index];
    }

    public void setStockPrice(String name, int newPrice) {
        int index = findStock(name);

        if(index == -1) {
            throw new IllegalArgumentException("Нет такого зарегестрированной акции: " + name);
        }

        this.stockPrices[index] = newPrice;
    }

    public String[] getStockNames() {
        return this.stockNames;
    }

    public int[] getStockPrices() {
        return this.stockPrices;
    }
}

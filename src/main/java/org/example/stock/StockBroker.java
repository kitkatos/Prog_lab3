package org.example.stock;

import org.example.person.MoneyHolder;

public class StockBroker {
    private final StockMarket stockMarket;

    public StockBroker(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
    }

    public void sellStock(MoneyHolder buyer, StockPortfolio portfolio, String stockName, int count) {
        portfolio.removeStock(stockName, count);

        int money = count * stockMarket.getStockPrice(stockName);

        buyer.reciveMoney(money);
    }

    public void buyStock(MoneyHolder buyer, StockPortfolio portfolio, String stockName, int count) {
        int money = count * stockMarket.getStockPrice(stockName);

        buyer.extractMoney(money);

        portfolio.addStock(stockName, count);
    }
}

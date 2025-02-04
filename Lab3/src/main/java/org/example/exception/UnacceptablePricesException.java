package org.example.exception;

public class UnacceptablePricesException extends RuntimeException {
    public final int[] prices;

    public UnacceptablePricesException() {
        super();

        this.prices = new int[0];
    }

    public UnacceptablePricesException(int[] prices) {
        super();

        this.prices = prices;
    }

    @Override
    public String getMessage() {
        return "Недопустимые цены";
    }
}

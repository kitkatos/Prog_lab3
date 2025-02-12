package org.example.exception;

public class TooManyMoneyToExtractExcpetion extends RuntimeException {
    private String citizenName;
    private int amountMoneyToExtract;

    public TooManyMoneyToExtractExcpetion(String citizenName, int amountMoneyToExtract) {
        super();

        this.citizenName = citizenName;
        this.amountMoneyToExtract = amountMoneyToExtract;
    }

    @Override
    public String getMessage() {
        return String.format("У гражданина %s нет %d денег для изъятия", citizenName, amountMoneyToExtract);
    }
}

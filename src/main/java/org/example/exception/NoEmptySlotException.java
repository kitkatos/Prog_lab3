package org.example.exception;

public class NoEmptySlotException extends RuntimeException {
    public NoEmptySlotException() {
        super("Нет свободных мест");
    }
}

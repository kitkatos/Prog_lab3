package org.example.exception;

public class CannotPaySalaryException extends RuntimeException {
    public CannotPaySalaryException(String message) {
        super(message);
    }
}

package org.example.exception;

public class NotFoundElementInArrayException extends RuntimeException {
  public NotFoundElementInArrayException() {
    super("Элемент не был найден в массиве");
  }
}

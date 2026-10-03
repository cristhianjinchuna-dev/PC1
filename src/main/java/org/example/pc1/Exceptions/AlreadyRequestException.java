package org.example.pc1.Exceptions;

public class AlreadyRequestException extends RuntimeException {
  public AlreadyRequestException(String message) {
    super(message);
  }
}

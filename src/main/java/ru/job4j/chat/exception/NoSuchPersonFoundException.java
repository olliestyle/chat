package ru.job4j.chat.exception;

public class NoSuchPersonFoundException extends RuntimeException {

    public NoSuchPersonFoundException(String message) {
        super(message);
    }
}

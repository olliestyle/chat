package ru.job4j.chat.exception;

public class NoSuchRoleFoundException extends RuntimeException {

    public NoSuchRoleFoundException(String message) {
        super(message);
    }
}

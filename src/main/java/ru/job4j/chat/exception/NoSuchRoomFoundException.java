package ru.job4j.chat.exception;

public class NoSuchRoomFoundException extends RuntimeException {
    public NoSuchRoomFoundException(String message) {
        super(message);
    }
}

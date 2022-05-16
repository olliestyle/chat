package ru.job4j.chat.dto;

import java.util.Objects;

public class MessageTextDTO {

    private int id;

    private String textMessage;

    public int getId() {
        return id;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageTextDTO that = (MessageTextDTO) o;
        return id == that.id && Objects.equals(textMessage, that.textMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textMessage);
    }
}

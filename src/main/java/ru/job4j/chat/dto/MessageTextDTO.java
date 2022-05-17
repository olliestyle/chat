package ru.job4j.chat.dto;

import ru.job4j.chat.util.Operation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MessageTextDTO {

    @NotNull(message = "id must be non null", groups = Operation.OnUpdate.class)
    private Integer id;

    @NotBlank(message = "text message mustn't be blank", groups = Operation.OnUpdate.class)
    private String textMessage;

    public Integer getId() {
        return id;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setId(Integer id) {
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

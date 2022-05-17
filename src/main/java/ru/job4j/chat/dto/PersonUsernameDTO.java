package ru.job4j.chat.dto;

import ru.job4j.chat.util.Operation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PersonUsernameDTO {

    @NotNull(message = "id must be not null", groups = Operation.OnUpdate.class)
    private Integer id;

    @NotBlank(message = "username mustn't be blank", groups = Operation.OnUpdate.class)
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonUsernameDTO that = (PersonUsernameDTO) o;
        return id == that.id && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}

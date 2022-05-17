package ru.job4j.chat.dto;

import ru.job4j.chat.util.Operation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RoomNameDTO {

    @NotNull(message = "id must be not null", groups = Operation.OnUpdate.class)
    private Integer id;

    @NotBlank(message = "name mustn't be blank", groups = Operation.OnUpdate.class)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomNameDTO that = (RoomNameDTO) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

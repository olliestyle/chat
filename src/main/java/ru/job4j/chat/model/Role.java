package ru.job4j.chat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ru.job4j.chat.util.Operation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "id must be null, when create new role", groups = Operation.OnCreate.class)
    @NotNull(message = "id must be non null", groups = Operation.OnUpdate.class)
    private Integer id;

    @Column(name = "name")
    @NotNull(message = "name must be not null when create", groups = Operation.OnCreate.class)
    @NotNull(message = "name must be not null when update", groups = Operation.OnUpdate.class)
    @NotBlank(message = "name mustn't be blank when create", groups = Operation.OnCreate.class)
    @NotBlank(message = "name mustn't be blank when update", groups = Operation.OnUpdate.class)
    private String name;

    @JsonManagedReference(value = "roomDetails-role")
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<RoomDetails> roomDetailsList;

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

    public List<RoomDetails> getRoomDetailsList() {
        return roomDetailsList;
    }

    public void setRoomDetailsList(List<RoomDetails> roomDetailsList) {
        this.roomDetailsList = roomDetailsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

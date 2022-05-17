package ru.job4j.chat.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ru.job4j.chat.util.Operation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "id must be null, when create new person", groups = Operation.OnCreate.class)
    @NotNull(message = "id must be non null", groups = Operation.OnUpdate.class)
    private Integer id;

    @JsonInclude
    @Column(name = "username")
    @NotNull(message = "username must be not null when create", groups = Operation.OnCreate.class)
    @NotNull(message = "username must be not null when update", groups = Operation.OnUpdate.class)
    @NotBlank(message = "username mustn't be blank when create", groups = Operation.OnCreate.class)
    @NotBlank(message = "username mustn't be blank when update", groups = Operation.OnUpdate.class)
    private String username;

    @Column(name = "password")
    @NotNull(message = "password must be not null when create", groups = Operation.OnCreate.class)
    @NotNull(message = "password must be not null when update", groups = Operation.OnUpdate.class)
    @NotBlank(message = "password mustn't be blank when create", groups = Operation.OnCreate.class)
    @NotBlank(message = "password mustn't be blank when update", groups = Operation.OnUpdate.class)
    private String password;

    @JsonManagedReference(value = "room-person")
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<Message> messages;

    @JsonManagedReference(value = "roomDetails-person")
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private Set<RoomDetails> roomDetails;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Set<RoomDetails> getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(Set<RoomDetails> roomDetails) {
        this.roomDetails = roomDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id && Objects.equals(username, person.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", username='" + username + '\'' + '}';
    }
}

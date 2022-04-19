package ru.job4j.chat.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonInclude
    @Column(name = "username")
    private String username;

    @JsonManagedReference(value = "room-person")
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<Message> messages;

    @JsonManagedReference(value = "roomDetails-person")
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private Set<RoomDetails> roomDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

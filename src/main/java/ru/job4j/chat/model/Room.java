package ru.job4j.chat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @JsonManagedReference(value = "room-message")
    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private List<Message> messages;

    @JsonManagedReference(value = "roomDetails-room")
    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private Set<RoomDetails> roomDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Room room = (Room) o;
        return id == room.id && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

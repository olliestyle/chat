package ru.job4j.chat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ru.job4j.chat.util.Operation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "id must be null, when create new message", groups = Operation.OnCreate.class)
    @NotNull(message = "id must be non null", groups = Operation.OnUpdate.class)
    private Integer id;

    @Column(name = "text_message")
    @NotBlank(message = "text message mustn't be blank", groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    private String textMessage;

    @JsonBackReference(value = "room-person")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "person_id")
    @NotNull(message = "can't create message without person", groups = Operation.OnCreate.class)
    @NotNull(message = "can't update message without person", groups = Operation.OnUpdate.class)
    private Person person;

    @JsonBackReference(value = "room-message")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "room_id")
    @NotNull(message = "you must choose room to send the message", groups = Operation.OnCreate.class)
    @NotNull(message = "you must choose room to update the message", groups = Operation.OnUpdate.class)
    private Room room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", textMessage='" + textMessage + '\'' + '}';
    }
}

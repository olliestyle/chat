package ru.job4j.chat.util;

import org.springframework.stereotype.Component;
import ru.job4j.chat.dto.MessageTextDTO;
import ru.job4j.chat.dto.PersonUsernameDTO;
import ru.job4j.chat.dto.RoleNameDTO;
import ru.job4j.chat.dto.RoomNameDTO;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.model.Room;

@Component
public class Mapper {

    public Message toMessage(MessageTextDTO messageTextDTO) {
        Message message = new Message();
        message.setId(messageTextDTO.getId());
        message.setTextMessage(messageTextDTO.getTextMessage());
        return message;
    }

    public Person toPerson(PersonUsernameDTO personUsernameDTO) {
        Person person = new Person();
        person.setId(personUsernameDTO.getId());
        person.setUsername(personUsernameDTO.getUsername());
        return person;
    }

    public Room toRoom(RoomNameDTO roomNameDTO) {
        Room room = new Room();
        room.setId(roomNameDTO.getId());
        room.setName(roomNameDTO.getName());
        return room;
    }

    public Role toRole(RoleNameDTO roleNameDTO) {
        Role role = new Role();
        role.setId(roleNameDTO.getId());
        role.setName(roleNameDTO.getName());
        return role;
    }

}

package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private final MessageRepository messageRepository;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final RoomRepository roomRepository;
    private final RoomDetailsRepository roomDetailsRepository;

    public ChatService(MessageRepository messageRepository,
                          PersonRepository personRepository,
                          RoleRepository roleRepository,
                          RoomRepository roomRepository,
                          RoomDetailsRepository roomDetailsRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.roomRepository = roomRepository;
        this.roomDetailsRepository = roomDetailsRepository;
    }


    public List<Message> findAllMessagesByRoomId(int id) {
        return messageRepository.findAllMessagesByRoomId(id);
    }

    public List<Person> findAllPersonsByRoomId(int id) {
        return personRepository.findAllPersonsByRoomId(id);
    }

    public List<Message> findAllMessagesByRoomIdAndPersonId(int roomId, int personId) {

        return messageRepository.findAllMessagesByRoomIdAndPersonId(roomId, personId);
    }

    public List<Message> findAllMessagesByPersonId(int personId) {
        return messageRepository.findAllMessagesByPersonId(personId);
    }
}

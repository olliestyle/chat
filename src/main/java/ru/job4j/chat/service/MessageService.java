package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.chat.exception.NoSuchPersonFoundException;
import ru.job4j.chat.exception.NoSuchRoomFoundException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.repository.MessageRepository;
import ru.job4j.chat.repository.PersonRepository;
import ru.job4j.chat.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;

    public MessageService(MessageRepository messageRepository, PersonRepository personRepository, RoomRepository roomRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
    }

    public Message save(Message message) {
        personRepository.findById(message.getPerson().getId())
                .ifPresentOrElse(
                        message::setPerson,
                        () -> {
                                throw new NoSuchPersonFoundException("There is no person with this id");
                        }
                );
        roomRepository.findById(message.getRoom().getId())
                .ifPresentOrElse(
                        message::setRoom,
                        () -> {
                                throw new NoSuchRoomFoundException("There is no room with this id");
                        }
                );
        messageRepository.save(message);
        return message;
    }

    public List<Message> findAll() {
        return (List<Message>) messageRepository.findAll();
    }

    public Optional<Message> findById(int id) {
        return messageRepository.findById(id);
    }

    public void delete(int id) {
        messageRepository.delete(messageRepository.findById(id).get());
    }
}

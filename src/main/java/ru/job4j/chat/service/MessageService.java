package ru.job4j.chat.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.exception.NoSuchPersonFoundException;
import ru.job4j.chat.exception.NoSuchRoomFoundException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.MessageRepository;
import ru.job4j.chat.repository.PersonRepository;
import ru.job4j.chat.repository.RoomRepository;

import java.security.Principal;
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

    public Message save(Message message, Principal principal) {
        Person person = personRepository.findPersonByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(principal.getName() + " not found"));
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
        if (person.getId() == message.getPerson().getId()) {
            messageRepository.save(message);
        } else {
            throw new IllegalStateException("Not enough rigths for saving message with this user id");
        }
        return message;
    }

    @Transactional
    public void update(Message message) {
        messageRepository.update(message.getId(), message.getTextMessage());
    }

    public List<Message> findAll() {
        return (List<Message>) messageRepository.findAll();
    }

    public Optional<Message> findById(Integer id) {
        return messageRepository.findById(id);
    }

    public void delete(Integer id) {
        messageRepository.delete(messageRepository.findById(id).get());
    }
}

package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.ChatService;
import ru.job4j.chat.service.PersonService;
import ru.job4j.chat.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final RoomService roomService;
    private final PersonService personService;

    public ChatController(ChatService chatService, RoomService roomService, PersonService personService) {
        this.chatService = chatService;
        this.roomService = roomService;
        this.personService = personService;
    }

    @GetMapping("/room/{roomId}/message")
    public List<Message> findAllMessagesByRoomId(@PathVariable int roomId) {
        if (roomService.findById(roomId).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room not found."
            );
        }
        return chatService.findAllMessagesByRoomId(roomId);
    }

    @GetMapping("/room/{roomId}/person")
    public List<Person> findAllPersonsByRoomId(@PathVariable int roomId) {
        if (roomService.findById(roomId).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room not found."
            );
        }
        return chatService.findAllPersonsByRoomId(roomId);
    }

    @GetMapping("/room/{roomId}/person/{personId}/message")
    public List<Message> findAllMessagesByRoomIdAndPersonId(@PathVariable int roomId, @PathVariable int personId) {
        if (roomService.findById(roomId).isEmpty() || personService.findById(personId).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room or Person not found."
            );
        }
        return chatService.findAllMessagesByRoomIdAndPersonId(roomId, personId);
    }

    @GetMapping("/person/{personId}/message")
    public List<Message> findAllMessagesByPersonId(@PathVariable int personId) {
        if (personService.findById(personId).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person not found."
            );
        }
        return chatService.findAllMessagesByPersonId(personId);
    }

}

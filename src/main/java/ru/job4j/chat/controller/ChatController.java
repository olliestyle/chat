package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Message>> findAllMessagesByRoomId(@PathVariable int roomId) {
        if (roomService.findById(roomId).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room not found."
            );
        }
        return new ResponseEntity<>(
                chatService.findAllMessagesByRoomId(roomId),
                HttpStatus.OK);
    }

    @GetMapping("/room/{roomId}/person")
    public ResponseEntity<List<Person>> findAllPersonsByRoomId(@PathVariable int roomId) {
        if (roomService.findById(roomId).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room not found."
            );
        }
        var body = chatService.findAllPersonsByRoomId(roomId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @GetMapping("/room/{roomId}/person/{personId}/message")
    public ResponseEntity<List<Message>> findAllMessagesByRoomIdAndPersonId(@PathVariable int roomId, @PathVariable int personId) {
        if (roomService.findById(roomId).isEmpty() || personService.findById(personId).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room or Person not found."
            );
        }
        return new ResponseEntity<>(
                chatService.findAllMessagesByRoomIdAndPersonId(roomId, personId),
                HttpStatus.OK
                );
    }

    @GetMapping("/person/{personId}/message")
    public ResponseEntity<List<Message>> findAllMessagesByPersonId(@PathVariable int personId) {
        if (personService.findById(personId).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person not found."
            );
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatService.findAllMessagesByPersonId(personId));
    }

}

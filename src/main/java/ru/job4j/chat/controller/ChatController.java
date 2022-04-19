package ru.job4j.chat.controller;

import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/room/{roomId}/message")
    public List<Message> findAllMessagesByRoomId(@PathVariable int roomId) {
        return chatService.findAllMessagesByRoomId(roomId);
    }

    @GetMapping("/room/{roomId}/person")
    public List<Person> findAllPersonsByRoomId(@PathVariable int roomId) {
        return chatService.findAllPersonsByRoomId(roomId);
    }

    @GetMapping("/room/{roomId}/person/{personId}/message")
    public List<Message> findAllMessagesByRoomIdAndPersonId(@PathVariable int roomId, @PathVariable int personId) {
        return chatService.findAllMessagesByRoomIdAndPersonId(roomId, personId);
    }

    @GetMapping("/person/{personId}/message")
    public List<Message> findAllMessagesByPersonId(@PathVariable int personId) {
        return chatService.findAllMessagesByPersonId(personId);
    }

}

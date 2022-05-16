package ru.job4j.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.MessageTextDTO;
import ru.job4j.chat.exception.NoSuchPersonFoundException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.service.MessageService;
import ru.job4j.chat.service.PersonService;
import ru.job4j.chat.util.Mapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class.getSimpleName());

    private final MessageService messageService;
    private final ObjectMapper objectMapper;
    private final Mapper mapper;


    public MessageController(MessageService messageService,
                             Mapper mapper,
                             ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public List<Message> findAll() {
        return messageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        Optional<Message> message = messageService.findById(id);
        return new ResponseEntity<>(
                message.orElse(new Message()),
                message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody Message message, Principal principal) {
        return new ResponseEntity<>(
                this.messageService.save(message, principal),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Message message, Principal principal) {
        messageService.save(message, principal);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/")
    public ResponseEntity<Void> update(@RequestBody MessageTextDTO messageTextDTO) {
        messageService.update(mapper.toMessage(messageTextDTO));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = { NoSuchPersonFoundException.class })
    public void exceptionHandler(Exception e,
                                 HttpServletRequest req,
                                 HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {{
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }

}

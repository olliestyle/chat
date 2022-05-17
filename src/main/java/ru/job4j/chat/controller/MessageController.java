package ru.job4j.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.MessageTextDTO;
import ru.job4j.chat.exception.NoSuchPersonFoundException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.service.MessageService;
import ru.job4j.chat.util.Mapper;
import ru.job4j.chat.util.Operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@Validated
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
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Message> create(@Valid @RequestBody Message message, Principal principal) {
        return new ResponseEntity<>(
                this.messageService.save(message, principal),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Message message, Principal principal) {
        messageService.save(message, principal);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/")
    public ResponseEntity<Void> update(@NotNull @Validated(Operation.OnUpdate.class) @RequestBody MessageTextDTO messageTextDTO) {
        messageService.update(mapper.toMessage(messageTextDTO));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Min(value = 1, message = "id must be >= 1") @PathVariable Integer id) {
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

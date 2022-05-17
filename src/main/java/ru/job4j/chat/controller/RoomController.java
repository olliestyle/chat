package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.RoomNameDTO;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.RoomService;
import ru.job4j.chat.util.Mapper;
import ru.job4j.chat.util.Operation;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
@Validated
public class RoomController {

    private final RoomService roomService;
    private final Mapper mapper;

    public RoomController(RoomService roomService, Mapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        Optional<Room> room = roomService.findById(id);
        return new ResponseEntity<>(
                room.orElse(new Room()),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Room> create(@Valid @RequestBody Room room) {
        if (room.getName() == null) {
            throw new NullPointerException("Name of room mustn't be empty");
        }
        return new ResponseEntity<>(
                roomService.save(room),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Room room) {
        roomService.save(room);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/")
    public ResponseEntity<Void> update(@NotNull @Validated({Operation.OnUpdate.class}) @RequestBody RoomNameDTO roomNameDTO) {
        roomService.update(mapper.toRoom(roomNameDTO));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Min(value = 1, message = "id must be >= 1") @PathVariable int id) {
        roomService.delete(id);
        return ResponseEntity.ok().build();
    }

}

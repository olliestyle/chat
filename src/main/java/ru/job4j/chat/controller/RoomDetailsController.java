package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.RoomDetails;
import ru.job4j.chat.service.RoomDetailsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roomDetails")
public class RoomDetailsController {

    private final RoomDetailsService roomDetailsService;

    public RoomDetailsController(RoomDetailsService roomDetailsService) {
        this.roomDetailsService = roomDetailsService;
    }

    @GetMapping("/")
    public List<RoomDetails> findAll() {
        return roomDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDetails> findById(@PathVariable int id) {
        Optional<RoomDetails> roomDetails = roomDetailsService.findById(id);
        return new ResponseEntity<>(
                roomDetails.orElse(new RoomDetails()),
                roomDetails.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<RoomDetails> create(@RequestBody RoomDetails roomDetails) {
        return new ResponseEntity<>(
                roomDetailsService.save(roomDetails),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody RoomDetails roomDetails) {
        roomDetailsService.save(roomDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        roomDetailsService.delete(id);
        return ResponseEntity.ok().build();
    }
}

package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return (List<Room>) roomRepository.findAll();
    }

    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void delete(int id) {
        roomRepository.delete(roomRepository.findById(id).get());
    }

}

package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.chat.exception.NoSuchPersonFoundException;
import ru.job4j.chat.exception.NoSuchRoleFoundException;
import ru.job4j.chat.exception.NoSuchRoomFoundException;
import ru.job4j.chat.model.RoomDetails;
import ru.job4j.chat.repository.PersonRepository;
import ru.job4j.chat.repository.RoleRepository;
import ru.job4j.chat.repository.RoomDetailsRepository;
import ru.job4j.chat.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomDetailsService {

    private final RoomDetailsRepository roomDetailsRepository;
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;
    private final RoleRepository roleRepository;

    public RoomDetailsService(RoomDetailsRepository roomDetailsRepository,
                              PersonRepository personRepository,
                              RoomRepository roomRepository,
                              RoleRepository roleRepository) {
        this.roomDetailsRepository = roomDetailsRepository;
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
        this.roleRepository = roleRepository;
    }

    public List<RoomDetails> findAll() {
        return (List<RoomDetails>) roomDetailsRepository.findAll();
    }

    public Optional<RoomDetails> findById(int id) {
        return roomDetailsRepository.findById(id);
    }

    public RoomDetails save(RoomDetails roomDetails) {
        personRepository.findById(roomDetails.getPerson().getId())
                .ifPresentOrElse(
                        roomDetails::setPerson,
                        () -> {
                            throw new NoSuchPersonFoundException("There is no person with this id");
                        }
                );
        roomRepository.findById(roomDetails.getRoom().getId())
                .ifPresentOrElse(
                        roomDetails::setRoom,
                        () -> {
                            throw new NoSuchRoomFoundException("There is no room with this id");
                        }
                );
        roleRepository.findById(roomDetails.getRole().getId())
                .ifPresentOrElse(
                        roomDetails::setRole,
                        () -> {
                            throw new NoSuchRoleFoundException("There is no role with this id");
                        }
                );
        roomDetailsRepository.save(roomDetails);
        return roomDetails;
    }

    public void delete(int id) {
        roomDetailsRepository.delete(roomDetailsRepository.findById(id).get());
    }
}

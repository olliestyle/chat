package ru.job4j.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.model.RoomDetails;

public interface RoomDetailsRepository extends CrudRepository<RoomDetails, Integer> {
}

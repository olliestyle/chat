package ru.job4j.chat.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.chat.model.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findAllMessagesByRoomId(int id);
    List<Message> findAllMessagesByRoomIdAndPersonId(int roomId, int personId);
    List<Message> findAllMessagesByPersonId(int personId);

    @Modifying
    @Query("update Message m set m.textMessage = :textMessage where m.id = :id")
    void update(@Param(value = "id") int id, @Param(value = "textMessage") String textMessage);
}

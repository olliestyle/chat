package ru.job4j.chat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.model.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("select p from Person p join p.roomDetails rd where rd.room.id = ?1")
    List<Person> findAllPersonsByRoomId(int id);

}

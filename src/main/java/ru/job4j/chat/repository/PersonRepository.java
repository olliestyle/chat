package ru.job4j.chat.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.chat.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("select p from Person p join p.roomDetails rd where rd.room.id = ?1")
    List<Person> findAllPersonsByRoomId(int id);

    Optional<Person> findPersonByUsername(String username);

    @Modifying
    @Query("update Person p set p.username = :username where p.id = :id")
    void update(@Param("id") int id, @Param("username") String username);

}

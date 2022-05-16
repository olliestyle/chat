package ru.job4j.chat.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.chat.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Modifying
    @Query("update Role r set r.name = :name where r.id = :id")
    void update(@Param("id") int id, @Param("name") String name);
}

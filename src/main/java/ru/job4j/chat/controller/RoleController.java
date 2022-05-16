package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.RoleNameDTO;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.service.RoleService;
import ru.job4j.chat.util.Mapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private final Mapper mapper;

    public RoleController(RoleService roleService, Mapper mapper) {
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable int id) {
        Optional<Role> role = roleService.findById(id);
        return new ResponseEntity<>(
                role.orElse(new Role()),
                role.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        if (role.getName() == null) {
            throw new NullPointerException("Name of role mustn't be empty");
        }
        return new ResponseEntity<>(
                roleService.save(role),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Role role) {
        roleService.save(role);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/")
    public ResponseEntity<Void> update(@RequestBody RoleNameDTO roleNameDTO) {
        roleService.update(mapper.toRole(roleNameDTO));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}

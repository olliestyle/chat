package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.RoleNameDTO;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.service.RoleService;
import ru.job4j.chat.util.Mapper;
import ru.job4j.chat.util.Operation;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
@Validated
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
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
        /*if (role.getName() == null) {
            throw new NullPointerException("Name of role mustn't be empty");
        }*/
        return new ResponseEntity<>(
                roleService.save(role),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Role role) {
        roleService.save(role);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/")
    public ResponseEntity<Void> update(@NotNull @Validated({Operation.OnUpdate.class}) @RequestBody RoleNameDTO roleNameDTO) {
        roleService.update(mapper.toRole(roleNameDTO));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Min(value = 1, message = "id must be >= 1") @PathVariable int id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}

package ru.job4j.chat.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.PersonService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private PersonService personService;
    private BCryptPasswordEncoder passwordEncoder;

    public AuthController(PersonService personService, BCryptPasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personService.save(person);
    }
}

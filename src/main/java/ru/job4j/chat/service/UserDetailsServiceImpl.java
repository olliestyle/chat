package ru.job4j.chat.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Person;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PersonService personService;

    public UserDetailsServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personService
                .findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}

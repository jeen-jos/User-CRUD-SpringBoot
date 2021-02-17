package com.sample.springboot2restservicebasic.resource;

import com.sample.springboot2restservicebasic.exception.UserNotFoundException;
import com.sample.springboot2restservicebasic.model.User;
import com.sample.springboot2restservicebasic.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUserss() {
        return userRepository.findAll();
    }


    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);

        return user.get();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);
        userRepository.deleteById(id);
    }


    @PostMapping("/users")
    public ResponseEntity<Object> createUsers(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }


    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUsers(@RequestBody User user, @PathVariable long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();

        user.setId(id);

        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }


}
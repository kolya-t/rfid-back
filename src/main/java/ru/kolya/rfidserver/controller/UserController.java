package ru.kolya.rfidserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kolya.rfidserver.model.User;
import ru.kolya.rfidserver.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{cardNumber}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUserByCardNumber(@PathVariable("cardNumber") String cardNumber) {
        User user = userRepository.findByCardNumber(cardNumber);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<User> createUser(User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }
}

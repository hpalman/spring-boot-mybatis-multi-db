package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.UserSchema1;
import com.example.mybatisdemo.service.UserSchema1Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schema1/users")
public class UserSchema1Controller {

    private final UserSchema1Service userService;

    public UserSchema1Controller(UserSchema1Service userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserSchema1> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSchema1> getUserById(@PathVariable Long id) {
        UserSchema1 user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<UserSchema1> createUser(@RequestBody UserSchema1 user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSchema1> updateUser(@PathVariable Long id, @RequestBody UserSchema1 user) {
        user.setId(id);
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

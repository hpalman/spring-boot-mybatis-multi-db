package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.UserSchema2;
import com.example.mybatisdemo.service.UserSchema2Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schema2/users")
public class UserSchema2Controller {

    private final UserSchema2Service userService;

    public UserSchema2Controller(UserSchema2Service userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserSchema2> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSchema2> getUserById(@PathVariable Long id) {
        UserSchema2 user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<UserSchema2> createUser(@RequestBody UserSchema2 user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSchema2> updateUser(@PathVariable Long id, @RequestBody UserSchema2 user) {
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

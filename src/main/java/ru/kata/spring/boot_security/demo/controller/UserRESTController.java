package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRESTController {

    private final UserService userService;

    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/admin/users/{id}")
    ResponseEntity<Optional<User>> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/admin/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        if (userService.loadUserByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            userService.add(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping ("/admin/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/roles")
    public ResponseEntity<List<Role>> showAllRoles() {
        return ResponseEntity.ok(userService.listRoles());
    }

    @GetMapping("/user/log")
    public ResponseEntity<User> showLoggedUser(Authentication authentication) {
        return ResponseEntity.ok((User) authentication.getPrincipal());
    }
}

package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRESTController {

    private UserService userService;

    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public List<User> showAllUsers() {
        return userService.listUsers();
    }

    @GetMapping("/admin/users/{id}")
    public Optional<User> getUser(@PathVariable int id) {
        return userService.findById(id);
    }

    @PutMapping("/admin/users")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @PostMapping("/admin/users")
    public void addNewUser(@RequestBody User user) {
        userService.add(user);
    }

    @DeleteMapping ("/admin/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.delete(id);
    }

    @GetMapping("/admin/roles")
    public List<Role> showAllRoles() {
        return userService.listRoles();
    }

    @GetMapping("/user/log")
    public User showLoggedUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}

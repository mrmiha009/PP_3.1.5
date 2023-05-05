package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void add(User user);

    List<User> listUsers();

    Optional<User> findById(Integer id);

    void delete(Integer id);

    void update(User user);

    UserDetails loadUserByUsername(String username);

    List<Role> listRoles();
}

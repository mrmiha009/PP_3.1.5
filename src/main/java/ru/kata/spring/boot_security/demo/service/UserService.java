package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @PersistenceContext
    private EntityManager em;

    public UserService(UserRepository userRepository, RoleRepository roleRepository1) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository1;
    }

    @Transactional
    public void add(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null){
            userRepository.save(user);
        }
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User getUser(Integer id) {
        return userRepository.getById(id);
    }

    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return em.createQuery("select u from User u left join fetch u.roles where u.username=:username",
                        User.class).setParameter("username", username).getSingleResult();
    }

    @Transactional
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

}

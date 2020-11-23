package com.example.demo.service;
import com.example.demo.model.Role;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    @Override
    public void saveUser(User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        user.setRoles(new HashSet<Role>());
        user.getRoles().add(roleRepository.findByRole("USER"));
        user.setActive(1);
        System.out.println(user);
        userRepository.save(user);
    }

    @Override
    public void initialSaveUser(Long id, String login, String pass, String role) {
        User user = new User();
        user.setLogin(login);
        user.setPass(pass);
        user.setPass(passwordEncoder.encode(user.getPass()));
        user.setRoles(new HashSet<Role>());
        user.getRoles().add(roleRepository.findByRole(role));
        user.setActive(1);
        System.out.println(user);
        userRepository.save(user);
    }
}

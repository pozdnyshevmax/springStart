package com.example.demo.service;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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

   /* @Override
    public List<User> findAllByRole(String role) {
        return null;
    }*/

    @Override
    public User saveUser(User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        user.setRole(roleRepository.findByRole("ADMIN"));
        return userRepository.save(user);
    }
}

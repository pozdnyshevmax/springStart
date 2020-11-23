package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    public User findByLogin(String login);

    public void saveUser(User user);

    public void initialSaveUser(Long id, String login, String pass, String role);
}

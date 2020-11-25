package com.example.demo.config;

import com.example.demo.model.Post;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void initializeDatabase() throws Exception {
        //initial roles
        if(!(roleRepository.findByRole("ADMIN") instanceof Role))
            roleRepository.save(new Role(1l,"ADMIN"));
        if(!(roleRepository.findByRole("USER") instanceof Role))
            roleRepository.save(new Role(2l,"USER"));
        //initial main users
        if(!(userService.findByLogin("ADMIN") instanceof User))
            userService.initialSaveUser(1L,"ADMIN","123", "ADMIN");
        if(!(userService.findByLogin("USER") instanceof User))
            userService.initialSaveUser(1L,"USER","123", "USER");
        //initial posts
        if(!(postRepository.findByTitle("first") instanceof Post))
            postRepository.save(new Post("first",new Date(),userService.findByLogin("ADMIN"),"fisrt POST"));
        if(!(postRepository.findByTitle("second") instanceof Post))
            postRepository.save(new Post("second",new Date(),userService.findByLogin("ADMIN"),"second POST"));
    }

}

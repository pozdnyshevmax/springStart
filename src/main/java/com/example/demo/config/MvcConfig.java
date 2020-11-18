package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private RoleRepository roleRepository;

    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        DelegatingPasswordEncoder passwordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        passwordEncoder.setDefaultPasswordEncoderForMatches(new MessageDigestPasswordEncoder("MD5"));
        return passwordEncoder;
    }

    @Bean
    public void initializeDatabase() throws Exception {
        roleRepository.save(new Role(1l,"ADMIN"));
        roleRepository.save(new Role(2l,"USER"));
    }

    /*@Bean
    public ServletRegistrationBean servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean<Servlet>(WebServlet);
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }*/

}

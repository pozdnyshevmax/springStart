package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value={ "/" , "/login"})
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }


    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(userService.findByLogin(user.login) instanceof User)
            bindingResult.rejectValue("login", "error.user","this User already exist!");
        else {
           // user.setRole(roleService.findByRole("ADMIN"));
            userService.saveUser(user);
            modelAndView.addObject("successMessage","Welcome! Now you are user!");
            modelAndView.addObject("user",user);
        }
        return "registration";
    }

}

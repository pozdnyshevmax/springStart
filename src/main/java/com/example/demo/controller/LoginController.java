package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value={ "/" , "/login"})
    public String loginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping(value="/admin/adminHome")
    public String adminHome(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User existUser = userService.findByLogin(authentication.getName());
        model.addAttribute("adminMessage","HELlOW ADMIN enjoyer");
        return "admin/adminHome";
    }

    @GetMapping(value="/user/userHome")
    public String userHome(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User existUser = userService.findByLogin(authentication.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userMessage","HELlOW standart user");
        return "user/userHome";
    }


    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(userService.findByLogin(user.getLogin()) instanceof User)
            bindingResult.rejectValue("login", "error.user","this User already exist!");
        else {
            user.setRoles(userService.findByLogin("USER").getRoles());
            userService.saveUser(user);
            modelAndView.addObject("successMessage","Welcome! Now you are user!");
        }
        modelAndView.addObject("user", new User());
        return "registration";
    }

}

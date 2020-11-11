package com.example.demo;

import com.example.demo.entyty.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    private Map<String,User> cach = new HashMap();
    private Map<String,String> users = new HashMap();

    //отображение страниц логина в зависимости от того зарегистрирован логин или нет
    // и есть ли номер сессии в мапе
    @GetMapping
    public String login(Model model){
        System.out.println(model.asMap().toString());
        String sesId = RequestContextHolder.currentRequestAttributes().getSessionId();
        System.out.println(sesId);
        if(cach.containsKey(sesId)){
            model.addAttribute("user",cach.get(sesId));
            System.out.printf("Resetly");
            return "hellowResently";
        } else
            model.addAttribute("user", new User());
        return "hellowAnon";
    }

    @PostMapping
    public String addUser(User user){
        String sesId = RequestContextHolder.currentRequestAttributes().getSessionId();
       if(!users.containsKey(user.getLogin())){
           cach.put(sesId, user);
           users.put(user.getLogin(), user.getPass());
           return "hellowByName";
       } else {
           if(users.get(user.getLogin()).equals(user.getPass()))
               return "hellowByName";
       }
        return "hellowError";
    }

}

package com.example.donate.controller;

import com.example.donate.model.User;
import com.example.donate.service.UserService;
import com.example.donate.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login - Donate");
        return "login";
    }


    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("email")String email, @RequestParam("password")String password, Model model,
                          HttpSession session) {
        User user = userService.findUserByUserName(email);
        if(user != null ) {
            boolean isMatched = passwordEncoder.matches(password, user.getPassword());
            if(isMatched) {
                session.setAttribute("user", user);
                return "redirect:/home";
            } else {
                session.setAttribute("message",
                        new Message("Invalid Username or password","alert-danger"));
            }

        } else {
            session.setAttribute("message",
                    new Message("Invalid Username or password","alert-danger"));
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("title", "SignUp - Donate");
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model,
                               HttpSession session) {
        try {
            if(result.hasErrors()) {
                model.addAttribute("user",user);
                return "signup";
            }
            user.setRole("GENERAL");
            user.setEnabled(true);
            System.out.println(user.getPassword());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            model.addAttribute("user", new User());
            session.setAttribute("message",new Message("Successfully Registered! ","alert-success"));
            return "signup";

        }catch(Exception e) {
            model.addAttribute("user",user);
            session.setAttribute("message",
                    new Message("Something went wrong!","alert-danger"));
            return "signup";
        }
    }

    @RequestMapping("/home")
    public String dashboard(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }
        model.addAttribute("title", "Home - Donate");
        return "home";
    }


    @RequestMapping("/about")
    public String about(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }
        model.addAttribute("title", "About - Donate");
        return "about";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}

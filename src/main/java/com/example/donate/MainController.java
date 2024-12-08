package com.example.donate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {


    @GetMapping("/")
    public String viewLoginPage(Model model) {
        return "redirect:/login";
    }
}

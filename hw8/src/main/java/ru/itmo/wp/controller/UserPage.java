package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable String id) {
        try {
            model.addAttribute("user", userService.findById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            //No operations;
        }
        return "UserPage";
    }
}

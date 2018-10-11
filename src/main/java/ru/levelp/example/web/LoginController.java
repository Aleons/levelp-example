package ru.levelp.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(method = RequestMethod.GET, path = "/signin")
    public String login() {
        return "signin";
    }
}

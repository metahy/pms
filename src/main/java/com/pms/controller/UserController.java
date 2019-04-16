package com.pms.controller;

import com.pms.domain.User;
import com.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index() {
        return "user/index";
    }

    @RequestMapping("encode")
    public String encode() {
        return "user/index";
    }

    @RequestMapping("decode")
    public String decode() {
        return "user/decode";
    }

    @RequestMapping("arithmetic")
    public String arithmetic() {
        return "user/arithmetic";
    }

    @RequestMapping("key")
    public String key() {
        return "user/key";
    }

    @RequestMapping("login")
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(User user, HttpSession session) {
        System.out.println("-> user -> login -> " + user);
        User userDB = userService.loginCheck(user);
        if (userDB != null) {
            session.setAttribute("id", userDB.getId());
            session.setAttribute("employeeNum", userDB.getRealName());
            session.setAttribute("realName", userDB.getUserName());
            return "redirect:/user";
        }
        return "redirect:/user/login";
    }
}

package com.pms.controller;

import com.pms.domain.Arithmetic;
import com.pms.domain.User;
import com.pms.domain.UserKey;
import com.pms.service.ArithmeticService;
import com.pms.service.UserKeyService;
import com.pms.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ArithmeticService arithmeticService;
    @Autowired
    private UserKeyService userKeyService;

    @RequestMapping("")
    public String index() {
        return "redirect:/user/encode";
    }

    @GetMapping("user")
    public String user(Model model, HttpSession session) {
        User user = userService.getUser((Integer) session.getAttribute("id"));
        model.addAttribute("user", user);
        List<UserKey> userKeys = userKeyService.getUserKeysByUserId(user.getId());
        model.addAttribute("userKeys", userKeys);
        return "user/user";
    }

    @PostMapping("user")
    public String userUpdate(User user, Model model, HttpSession session) {
        if (userService.updateUser(user) > 0) {
            session.setAttribute("id", user.getId());
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("realName", user.getRealName());
            session.setAttribute("role", user.getAdmin() ? "admin" : "user");
        }
        model.addAttribute("user", user);
        List<UserKey> userKeys = userKeyService.getUserKeysByUserId(user.getId());
        model.addAttribute("userKeys", userKeys);
        return "user/user";
    }

    @PostMapping("register")
    public String register(User user, Model model, HttpSession session) {
        User userDb = userService.register(user);
        if (userDb != null) {
            session.setAttribute("id", userDb.getId());
            session.setAttribute("userName", userDb.getUserName());
            session.setAttribute("realName", userDb.getRealName());
            session.setAttribute("role", userDb.getAdmin() ? "admin" : "user");
        }
        model.addAttribute("user", userDb);
        return "user/user";
    }

    @GetMapping("encodeBatch")
    public String encodeBatch(Model model) {
        List<Arithmetic> arithmetics = arithmeticService.getInUseArithmetic();
        model.addAttribute("arithmetics", arithmetics);
        return "user/encodeBatch";
    }

    @PostMapping("encodeBatch")
    public ResponseEntity<byte[]> encodeFile(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "attchement;filename=" + file.getOriginalFilename().substring(Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".")) + "-encode" + ".txt");
            return new ResponseEntity<>(FileUtils.readFileToByteArray(arithmeticService.encode(file)), httpHeaders, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("encode")
    public String encode(Model model) {
        List<Arithmetic> arithmetics = arithmeticService.getInUseArithmetic();
        model.addAttribute("arithmetics", arithmetics);
        return "user/index";
    }

    @PostMapping("encode")
    public String encode(UserKey userKey, Model model) {
        System.out.println(userKey);
        List<Arithmetic> arithmetics = arithmeticService.getInUseArithmetic();
        model.addAttribute("arithmetics", arithmetics);
        model.addAttribute("userKey", arithmeticService.encode(userKey));
        return "user/index";
    }

    @GetMapping("decodeBatch")
    public String decodeBatch(Model model) {
        List<Arithmetic> arithmetics = arithmeticService.getInUseArithmetic();
        model.addAttribute("arithmetics", arithmetics);
        return "user/decodeBatch";
    }

    @PostMapping("decodeBatch")
    public ResponseEntity<byte[]> decodeFile(@RequestParam(value = "file", required = false) MultipartFile file, String secretKey) throws IOException {
        if (!file.isEmpty()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "attchement;filename=" + file.getOriginalFilename().substring(Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".")) + "-decode" + ".txt");
            return new ResponseEntity<>(FileUtils.readFileToByteArray(arithmeticService.decode(file, secretKey)), httpHeaders, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("decode")
    public String decode() {
        return "user/decode";
    }

    @PostMapping("decode")
    public String decode(UserKey userKey, Model model) {
//        System.out.println(userKey);
//        List<Arithmetic> arithmetics = arithmeticService.getInUseArithmetic();
//        model.addAttribute("arithmetics", arithmetics);
//        model.addAttribute("userKey", arithmeticService.encode(userKey));
        return "user/decode";
    }

    @RequestMapping("arithmetic")
    public String arithmetic(String id, String inUse, Model model) {
        if (id != null) {
            arithmeticService.setArithmeticInUse(Integer.valueOf(id), inUse);
        }
        List<Arithmetic> arithmetics = arithmeticService.getAllArithmetic();
        model.addAttribute("arithmetics", arithmetics);
        return "user/arithmetic";
    }

    @RequestMapping("key")
    public String key(Model model) {
        List<Arithmetic> arithmetics = arithmeticService.getAllArithmetic();
        model.addAttribute("arithmetics", arithmetics);
        return "user/key";
    }

    @PostMapping("setSecretKey")
    public String setSecretKey(String AESSecretKey, String DESSecretKey, String PBESecretKey) {
        arithmeticService.updateSecretKey(1, AESSecretKey);
        arithmeticService.updateSecretKey(2, DESSecretKey);
        arithmeticService.updateSecretKey(3, PBESecretKey);
        return "redirect:/user/key";
    }

    @GetMapping("login")
    public String login() {
        return "user/login";
    }

    @PostMapping("login")
    public String login(User user, HttpSession session) {
        System.out.println("-> user -> login -> " + user);
        User userDB = userService.loginCheck(user);
        if (userDB != null) {
            session.setAttribute("id", userDB.getId());
            session.setAttribute("userName", userDB.getUserName());
            session.setAttribute("realName", userDB.getRealName());
            session.setAttribute("role", userDB.getAdmin() ? "admin" : "user");
            return "redirect:/user";
        }
        return "redirect:/user/login";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user/login";
    }

    @GetMapping("userKeyDel")
    public String userKeyDel(String id) {
        userKeyService.deleteUserKeyById(id);
        return "redirect:/user/user";
    }
}

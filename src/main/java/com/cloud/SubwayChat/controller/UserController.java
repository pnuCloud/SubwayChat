package com.cloud.SubwayChat.controller;

import com.cloud.SubwayChat.domain.SubwayLine;
import com.cloud.SubwayChat.domain.User;
import com.cloud.SubwayChat.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("lines", SubwayLine.values());

        return "createUser";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user, HttpSession session) {
        userService.join(user.getNickName(), user.getLine(), session);

        return "redirect:/";
    }
}

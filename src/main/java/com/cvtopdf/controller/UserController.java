package com.cvtopdf.controller;

import com.cvtopdf.service.UserDetailsImpl;
import com.cvtopdf.service.UserService;
import com.cvtopdf.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String userDetails(Model model){

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();
        model.addAttribute("user", user);

        return "user/main";
    }


}

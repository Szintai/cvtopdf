package com.cvtopdf.controller;

import com.cvtopdf.entity.Job;
import com.cvtopdf.service.UserDetailsImpl;
import com.cvtopdf.service.UserService;
import com.cvtopdf.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @GetMapping("/profile")
    public String userDetails(Model model){

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();
        model.addAttribute("user", user);

        return "user/main";
    }


    @GetMapping(value = "/profile/edit")
    public String findById( Model model) {
        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();
        model.addAttribute("user", user);

        return "user/edit";
    }

    @PostMapping(value = "/profile/edit")
    public String update( Model model, User userToUpdate) {



        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();

        userToUpdate.setId(user.getId());
        userToUpdate.setJobs(user.getJobs());
        userToUpdate.setStudies(user.getStudies());

        String encodedPassword = bCryptPasswordEncoder.encode(userToUpdate.getPassword());
        userToUpdate.setPassword(encodedPassword);
        model.addAttribute("user",  userService.save(userToUpdate));
        System.out.println(userToUpdate.toString());

        return "redirect:/profile";
    }


}

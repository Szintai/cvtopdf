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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {


    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private User authorizedUser;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }


    @GetMapping("/profile")
    public String userDetails(Model model){

        authorizedUser=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User user= userService.findById(authorizedUser.getId());


        model.addAttribute("user", user);

        return "user/main";
    }


    @GetMapping(value = "/profile/edit")
    public String findById( Model model) {

        model.addAttribute("user", userService.findById(authorizedUser.getId()));

        return "user/edit";
    }

    @PostMapping(value = "/profile/edit")
    public String update( Model model, User user) {

        user.setId(authorizedUser.getId());
        user.setJobs(userService.findById(authorizedUser.getId()).getJobs());
        user.setStudies(userService.findById(authorizedUser.getId()).getStudies());
        String encodedPassword="";
        encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        model.addAttribute("user",  userService.save(user));


        return "redirect:/profile";
    }

    @GetMapping("/profile/cv")
    public String cv(Model model){

        authorizedUser=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User user= userService.findById(authorizedUser.getId());


        model.addAttribute("user", user);

        return "user/cv";
    }



    @PostMapping(" /profile/cv/pictureUpload")
    public String prodUpload( @RequestParam("picture") MultipartFile picture) {


        return "redirect:/profile/cv";
    }


}

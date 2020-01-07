package com.cvtopdf.controller;


import com.cvtopdf.entity.LanguageExam;
import com.cvtopdf.entity.User;
import com.cvtopdf.service.LanguageExamService;
import com.cvtopdf.service.UserDetailsImpl;
import com.cvtopdf.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

@Controller
public class LanguageExamController {

    private final LanguageExamService languageExamService;

    private final UserService userService;

    private User authorizedUser;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public LanguageExamController(LanguageExamService languageExamService, UserService userService) {
        this.languageExamService = languageExamService;
        this.userService = userService;
    }

    @GetMapping("/profile/les")
    public String languageExams(Model model)
    {

        authorizedUser=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User user= userService.findById(authorizedUser.getId());


       /* for (LanguageExam le: user.getLanguageExams()
             ) {
            log.info(le.toString());
        }*/



        model.addAttribute("languageExams",user.getLanguageExams());

        return "le/main";
    }

    @GetMapping(value = "/profile/les/{id}/edit")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("le", languageExamService.findById(id));

        return "le/edit";
    }

    @PostMapping(value = "/profile/les/{id}/edit")
    public String update(@PathVariable("id") Long id, Model model, LanguageExam languageExam) {

        User user=userService.findById(authorizedUser.getId());

        languageExam.setUser(user);

        model.addAttribute("le",  languageExamService.save(languageExam));


        return "redirect:/profile/les";
    }


    @GetMapping(value ="/profile/les/new")
    public String newLe(Model model) {
        model.addAttribute("le", new LanguageExam());

        return "le/new";
    }

    @PostMapping(value ="/profile/les/new")
    public String newLeSave(@ModelAttribute LanguageExam languageExam) {

        User user=userService.findById(authorizedUser.getId());
        languageExam.setUser(user);
        if(user.getLanguageExams() != null) {
            user.getLanguageExams().add(languageExam);
        }
        else{
            Set<LanguageExam> les=new HashSet<>();
            les.add(languageExam);
            user.setLanguageExams(les);
        }
        userService.save(user);

        return "redirect:/profile/les";
    }

    @GetMapping(value = "/profile/les/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model) {

        Set<LanguageExam> lesOld=userService.findById(authorizedUser.getId()).getLanguageExams();
        Set<LanguageExam> les=new HashSet<>();
        languageExamService.deleteById(id);
        for (LanguageExam languageExam: lesOld) {

            if(languageExam.getId() != id){les.add(languageExam);}
        }

        User user= userService.findById(authorizedUser.getId());
        user.setLanguageExams(les);

        userService.save(user);

        return "redirect:/profile/les";
    }
}

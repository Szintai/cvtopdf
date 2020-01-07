package com.cvtopdf.controller;

import com.cvtopdf.entity.Job;
import com.cvtopdf.entity.Study;
import com.cvtopdf.service.StudyService;
import com.cvtopdf.service.UserDetailsImpl;
import com.cvtopdf.entity.User;
import com.cvtopdf.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class StudyController {

    private final StudyService studyService;

    private final UserService userService;

    private User authorizedUser;

    public StudyController(StudyService studyService, UserService userService) {
        this.studyService = studyService;
        this.userService = userService;
    }

    @GetMapping("/profile/studies")
    public String studies(Model model)
    {

        authorizedUser=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        User user=userService.findById(authorizedUser.getId());

        model.addAttribute("studies", user.getStudies());

        return "study/main";
    }

    @GetMapping(value = "/profile/studies/{id}/edit")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("study", studyService.findById(id));

        return "study/edit";
    }

    @PostMapping(value = "/profile/studies/{id}/edit")
    public String update(@PathVariable("id") Long id, Model model, Study study) {

       User user=userService.findById(authorizedUser.getId());

        study.setUser(user);

        model.addAttribute("study",  studyService.save(study));


        return "redirect:/profile/studies";
    }


    @GetMapping(value ="/profile/studies/new")
    public String newStudy(Model model) {
        model.addAttribute("study", new Study());

        return "study/new";
    }

    @PostMapping(value ="/profile/studies/new")
    public String newStudySave(@ModelAttribute Study study) {

         User user=userService.findById(authorizedUser.getId());
        study.setUser(user);
        user.getStudies().add(study);
        userService.save(user);


        return "redirect:/profile/studies";
    }

    @GetMapping(value = "/profile/studies/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model) {

        Set<Study> studyOld=userService.findById(authorizedUser.getId()).getStudies();
        Set<Study> studies=new HashSet<>();
        studyService.deleteById(id);
        for (Study study: studyOld) {

            if(study.getId() != id){studies.add(study);}
        }

        User user= userService.findById(authorizedUser.getId());
        user.setStudies(studies);

        userService.save(user);

        return "redirect:/profile/studies";
    }


}
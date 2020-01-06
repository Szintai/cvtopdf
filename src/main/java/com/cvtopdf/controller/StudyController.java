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

@Controller
public class StudyController {

    private final StudyService studyService;

    private final UserService userService;

    public StudyController(StudyService studyService, UserService userService) {
        this.studyService = studyService;
        this.userService = userService;
    }

    @GetMapping("/profile/studies")
    public String studies(Model model)
    {

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();

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

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();

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

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();
        user.getStudies().add(study);
        study.setUser(user);
        userService.save(user);
        studyService.save(study);

        return "redirect:/profile/studies";
    }


}
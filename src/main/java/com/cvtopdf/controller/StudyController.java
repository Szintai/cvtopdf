package com.cvtopdf.controller;

import com.cvtopdf.service.StudyService;
import com.cvtopdf.service.UserDetailsImpl;
import com.cvtopdf.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/profile/studies")
    public String studies(Model model)
    {

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();

        model.addAttribute("studies", user.getStudies());

        return "study/main";
    }

}
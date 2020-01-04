package com.cvtopdf.controller;

import com.cvtopdf.service.JobService;
import com.cvtopdf.service.UserDetailsImpl;
import com.cvtopdf.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobController {

        private final JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping("/profile/jobs")
    public String jobs(Model model)
    {

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();

        model.addAttribute("jobs", user.getJobs());

        return "job/main";
    }

}

package com.cvtopdf.controller;

import com.cvtopdf.entity.Job;
import com.cvtopdf.service.JobService;
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

import java.awt.print.Book;

@Controller
public class JobController {

        private final JobService jobService;

        private final UserService userService;


    public JobController(JobService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    @GetMapping("/profile/jobs")
    public String jobs(Model model)
    {

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();

        model.addAttribute("jobs", user.getJobs());

        return "job/main";
    }

    @GetMapping(value = "/profile/jobs/{id}/edit")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("job", jobService.findById(id));

        return "job/edit";
    }

    @PostMapping(value = "/profile/jobs/{id}/edit")
    public String update(@PathVariable("id") Long id, Model model, Job job) {

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();

        job.setUser(user);

        model.addAttribute("job",  jobService.save(job));


        return "redirect:/profile/jobs";
    }


    @GetMapping(value ="/profile/jobs/new")
    public String newJob(Model model) {
        model.addAttribute("job", new Job());

        return "job/new";
    }

    @PostMapping(value ="/profile/jobs/new")
    public String newJobSave(@ModelAttribute Job job) {

        UserDetailsImpl userDetails=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userDetails.getUser();
        user.getJobs().add(job);
        job.setUser(user);
        userService.save(user);
        jobService.save(job);

        return "redirect:/profile/jobs";
    }

}

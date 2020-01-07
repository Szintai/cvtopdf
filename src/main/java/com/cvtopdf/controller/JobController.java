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
import sun.reflect.generics.tree.Tree;

import java.awt.print.Book;
import java.util.*;

@Controller
public class JobController {

        private final JobService jobService;

        private final UserService userService;

        private User authorizedUser;


    public JobController(JobService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    @GetMapping("/profile/jobs")
    public String jobs(Model model)
    {

        authorizedUser=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User user= userService.findById(authorizedUser.getId());

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

        User user=userService.findById(authorizedUser.getId());

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

        User user=userService.findById(authorizedUser.getId());
        job.setUser(user);
        user.getJobs().add(job);
        userService.save(user);

        return "redirect:/profile/jobs";
    }

    @GetMapping(value = "/profile/jobs/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model) {

        Set<Job> jobsOld=userService.findById(authorizedUser.getId()).getJobs();
        Set<Job> jobs=new HashSet<>();
        jobService.deleteById(id);
        for (Job job: jobsOld) {

            if(job.getId() != id){jobs.add(job);}
        }

        User user= userService.findById(authorizedUser.getId());
        user.setJobs(jobs);

        userService.save(user);

        return "redirect:/profile/jobs";
    }



}

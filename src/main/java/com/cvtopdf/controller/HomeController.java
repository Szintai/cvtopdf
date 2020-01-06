package com.cvtopdf.controller;


import com.cvtopdf.entity.User;
import com.cvtopdf.service.UserService;
import com.cvtopdf.repository.JobRepository;
import com.cvtopdf.repository.RoleRepository;
import com.cvtopdf.repository.StudyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	private final Logger log= LoggerFactory.getLogger(this.getClass());


	private final UserService userService;
	private final RoleRepository roleRepository;
	private final JobRepository jobRepository;
	private final StudyRepository studyRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;


	public HomeController(UserService userService, RoleRepository roleRepository, JobRepository jobRepository, StudyRepository studyRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.roleRepository = roleRepository;
		this.jobRepository = jobRepository;
		this.studyRepository = studyRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@GetMapping("/")
	public String index()
	{

		
		return "home/main";
	}



	@GetMapping("/registration")
	public String registration(Model model)
	{
      model.addAttribute("user", new User());
		return "auth/registration";
	}

	@PostMapping("/registration")
	public String reg(@ModelAttribute User user)
	{

		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userService.save(user);

		return "redirect:login";

	}

	

	

	
	
	
}

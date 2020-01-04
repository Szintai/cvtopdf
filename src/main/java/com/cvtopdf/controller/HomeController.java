package com.cvtopdf.controller;


import com.cvtopdf.service.UserService;
import com.cvtopdf.repository.JobRepository;
import com.cvtopdf.repository.RoleRepository;
import com.cvtopdf.repository.StudyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private final Logger log= LoggerFactory.getLogger(this.getClass());

//Itt ezeket majd kivesszük
	private final UserService userService;
	private final RoleRepository roleRepository;
	private final JobRepository jobRepository;
	private final StudyRepository studyRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	boolean init=true;

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

		
		return "main/index";
	}


	@GetMapping("/proba")
	public String proba(){

		return "proba";
	}

	

	

	
	
	
}

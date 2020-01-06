package com.cvtopdf.config;


import com.cvtopdf.entity.Job;
import com.cvtopdf.entity.Study;
import com.cvtopdf.entity.User;
import com.cvtopdf.repository.RoleRepository;
import com.cvtopdf.service.JobService;
import com.cvtopdf.service.StudyService;
import com.cvtopdf.service.UserService;
import com.cvtopdf.entity.Role;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final UserService userService;
	private final JobService jobService;
	private final StudyService studyService;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public Bootstrap(UserService userService, JobService jobService, StudyService studyService, RoleRepository roleRepository,  BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.jobService = jobService;
		this.studyService = studyService;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initUsers();
	}

	public void initUsers()
	{
		//Test datas

		User user1 = new User(), user2=new User();
		Set<Job> jobs1=new HashSet<>();
		Set<Job> jobs2=new HashSet<>();
		Set<Study> studies1=new HashSet<>();


		Role role=new Role("USER");

		Set<User> users=new HashSet<>();

		user1.setEmail("a");
		user1.setFirstName("a");
		user1.setLastName("a");
		user1.setPassword("a");
		user1.setBirthDate(LocalDate.now());
		user1.setBirthPlace("Nagyatád");
		user1.setNationality("HUN");
		user1.setPhoneNumber("1234567");


		user2.setEmail("b");
		user2.setFirstName("b");
		user2.setLastName("b");
		user2.setPassword("b");
		user2.setBirthDate(LocalDate.now());
		user2.setBirthPlace("Nagyatád");
		user2.setNationality("HUN");
		user2.setPhoneNumber("1234567");

		String encodedPassword1 = bCryptPasswordEncoder.encode(user1.getPassword());
		String encodedPassword2 = bCryptPasswordEncoder.encode(user2.getPassword());
		user1.setPassword(encodedPassword1);
		user2.setPassword(encodedPassword2);

		user1.setEnabled(true);
		user2.setEnabled(true);


		Job job1 =new Job();
		Job job2 =new Job();

		Study study1=new Study();
		Study study2=new Study();

		job1.setName("Munka1");
		job2.setName("Munka2");
		job1.setStartDate(LocalDate.now());
		job2.setStartDate(LocalDate.now());
		job1.setEndDate(LocalDate.now());
		job2.setEndDate(LocalDate.now());
		job1.setPosition("Pozicio1");
		job2.setPosition("Pozicio2");
		job1.setScopeOfDuties("Munkakor1");
		job2.setScopeOfDuties("Munkakor2");






		study1.setName("Tanulmany1");
		study1.setStartDate(LocalDate.now());
		study1.setEndDate(LocalDate.now());
		study1.setResult("Megfelelt1");


		study2.setName("Tanulmány2");
		study2.setStartDate(LocalDate.now());
		study2.setEndDate(LocalDate.now());
		study2.setResult("Megfelelt2");


		job1.setUser(user1);
		job2.setUser(user2);
		study1.setUser(user1);
		study2.setUser(user1);

		jobs1.add(job1);
		jobs2.add(job2);
		studies1.add(study1);
		studies1.add(study2);
		user1.setJobs(jobs1);
		user2.setJobs(jobs2);
		user1.setStudies(studies1);


		user1.setRole(role);
		user2.setRole(role);
		role.setUsers(users);
		roleRepository.save(role);
		userService.save(user1);
		userService.save(user2);


		jobService.save(job1);
		jobService.save(job2);
		studyService.save(study1);
		studyService.save(study2);




	}

}

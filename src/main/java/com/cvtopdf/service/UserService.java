package com.cvtopdf.service;


import com.cvtopdf.entity.User;

public interface UserService {
	

	
	public User findByEmail(String email);

	User save(User user);
	

}

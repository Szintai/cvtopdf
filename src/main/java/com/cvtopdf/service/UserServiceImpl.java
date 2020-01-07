package com.cvtopdf.service;


import com.cvtopdf.entity.User;
import com.cvtopdf.repository.RoleRepository;
import com.cvtopdf.repository.UserRepository;
import com.cvtopdf.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final String USER_ROLE = "USER";

	private Role role =null;


	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(user);
	}



	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElse(new User());
	}

	@Override
	public User findById(Long id) {
		
		return userRepository.findById(id).orElse(new User());
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User save(User user)
	{

			if (!user.isEnabled()) {
				user.setEnabled(true);
				user.setRole(roleRepository.findByRole("USER"));
			}

			return userRepository.save(user);

	}

	
}

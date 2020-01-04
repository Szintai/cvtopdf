package com.cvtopdf.repository;



import com.cvtopdf.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	
	Optional<User> findById(Long id);

}

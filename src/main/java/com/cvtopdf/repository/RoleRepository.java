package com.cvtopdf.repository;


import com.cvtopdf.entity.Role;
import org.springframework.data.repository.CrudRepository;



public interface RoleRepository extends CrudRepository<Role, Long> {

	
	Role findByRole(String role);
	
	
	
}

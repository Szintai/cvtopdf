package com.cvtopdf.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "roles")
@SequenceGenerator(name = "default_gen", sequenceName = "role_seq", allocationSize = 1)
public class Role extends BaseEntity {
	
	private String role;


	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<User> users;
	
	public Role() {}
	
	public Role(String role) {
		this.role=role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
	

}

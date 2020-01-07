package com.cvtopdf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedSet;

@Entity
@Table( name = "users")
@SequenceGenerator(name = "default_gen", sequenceName = "user_seq", allocationSize = 1)
public class User extends BaseEntity{

	@Column( nullable = false)
	private String firstName;

	@Column( nullable = false)
	private String lastName;

	@Column( nullable = false,  unique = true)
	private String email;

	@Column( nullable = false)
	private String password;

	@Column( nullable = false)
	private String birthPlace;

	@Column( nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@Column( nullable = false)
	private String nationality;

	@Column( nullable = false)
	private String phoneNumber;

	@Column( nullable = false)
	private boolean enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Job> jobs;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Study> studies;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	
	public User() {}
	
	


	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public Set<Study> getStudies() {
		return studies;
	}

	public void setStudies(Set<Study> studies) {
		this.studies = studies;
	}

	@Override
	public String toString() {
		return "User{" +
				"id: " + id+ '\'' +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", birthPlace='" + birthPlace + '\'' +
				", birthDate=" + birthDate +
				", nationality='" + nationality + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", enabled=" + enabled +
				", jobs=" + jobs +
				", studies=" + studies +
				", role=" + role +
				'}';
	}
}

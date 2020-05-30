package it.uniroma3.siw.spring.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true, nullable = false, length = 100)
	private String username;
	
	@Column(unique = true, nullable = false, length = 100)
	private String password;
	
	@Column( nullable = false, length = 100)
	private String firstName;
	
	@Column( nullable = false, length = 100)
	private String lastName;
	
	@Column(updatable = false, nullable = false)
	private LocalDateTime creationTimeStamp;
	
	@Column( nullable = false)
	private LocalDateTime lastUpdateTimeStamp;
	
	
	@OneToMany(cascade = CascadeType.REMOVE,mappedBy = "owner")
	private List<Project> ownedProjects;
	
	@ManyToMany(mappedBy = "members")
	private List<Project> visibleProjects;
	
	public User() {
		// TODO Auto-generated constructor stub
		this.ownedProjects = new ArrayList<Project>();
		this.visibleProjects = new ArrayList<Project>();
	}
	
	public User(String username,String password, String firstName,String lastName) {
	
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@PrePersist
	protected void onPersist() {
		this.creationTimeStamp = LocalDateTime.now();
		this.lastUpdateTimeStamp = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.lastUpdateTimeStamp = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public LocalDateTime getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(LocalDateTime creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	public LocalDateTime getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}

	public void setLastUpdateTimeStamp(LocalDateTime lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}

	public List<Project> getOwnedProjects() {
		return ownedProjects;
	}

	public void setOwnedProjects(List<Project> ownedProjects) {
		this.ownedProjects = ownedProjects;
	}

	public List<Project> getVisibleProjects() {
		return visibleProjects;
	}

	public void setVisibleProjects(List<Project> visibleProjects) {
		this.visibleProjects = visibleProjects;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", creationTimeStamp=" + creationTimeStamp + ", lastUpdateTimeStamp=" + lastUpdateTimeStamp
				+ ", ownedProjects=" + ownedProjects + ", visibleProjects=" + visibleProjects + "]";
	}
	
	
}

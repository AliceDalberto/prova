package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@ManyToOne
	private User owner;

	@ManyToMany
	private List<User> members;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private List<Task> containedTasks;


	public Project() {
		// TODO Auto-generated constructor stub
		this.containedTasks = new ArrayList<Task>();
	}


	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}


	public void addMember(User user) {
		 members.add(user);
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}


	public List<User> getMembers() {
		return members;
	}


	public void setMembers(List<User> members) {
		this.members = members;
	}


	public List<Task> getContainedTasks() {
		return containedTasks;
	}


	public void setContainedTasks(List<Task> containedTasks) {
		this.containedTasks = containedTasks;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


}

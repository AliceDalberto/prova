package it.uniroma3.siw.spring.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String description;

	private boolean completed;

	@Column(nullable = false)
	private LocalDateTime creationTimeStamp;

	@Column(nullable = false)
	private LocalDateTime lastUpdateTimeStamp;
	

	public Task() {
		// TODO Auto-generated constructor stub
	}

	public Task(String name) {
		this.name = name;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}

	public void setLastUpdateTimeStamp(LocalDateTime lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}

	@PrePersist
	protected void pippo() {
		this.creationTimeStamp = LocalDateTime.now();
		this.lastUpdateTimeStamp = LocalDateTime.now();
	}


	@PreUpdate
	protected void pluto() {
		this.lastUpdateTimeStamp = LocalDateTime.now();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public LocalDateTime getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(LocalDateTime creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}

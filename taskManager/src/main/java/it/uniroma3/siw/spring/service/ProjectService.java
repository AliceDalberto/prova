package it.uniroma3.siw.spring.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Project;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	protected ProjectRepository projectRepository;
	
	@Transactional
	public Project saveProject(Project project) {
		return this.projectRepository.save(project);
	}
	
	@Transactional
	public void deleteProject(Project project) {
		 this.projectRepository.delete(project);
	}
	
	@Transactional
	public Project getProject(Long id) {
		Optional<Project>  project =  this.projectRepository.findById(id);
		return project.orElse(null);
	}
	
	@Transactional
	public Project shareProject(Project project,User user) {
		project.addMember(user);
		return this.projectRepository.save(project);
	}
	
}

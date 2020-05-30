package it.uniroma3.siw.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.uniroma3.siw.spring.model.Project;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.ProjectRepository;
import it.uniroma3.siw.spring.repository.TaskRepository;
import it.uniroma3.siw.spring.repository.UserRepository;
import it.uniroma3.siw.spring.service.ProjectService;
import it.uniroma3.siw.spring.service.TaskService;
import it.uniroma3.siw.spring.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskManagerApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private TaskRepository taskRepository;

	@Before
	public void deleteAll() {
		userRepository.deleteAll();
		projectRepository.deleteAll();
		taskRepository.deleteAll();
	}

	@Test
	public void userUpdate() {

		//primo utente
		User user = new User("marioss","password", "mario", "rossi");
		user = userService.saveUser(user);
		assertEquals(user.getId(), 1L);

		//utente aggiornato
		User updateUser = new User("mariab", "password", "maria", "bianchi");
		updateUser.setId(user.getId());
		user =userService.saveUser(updateUser);
		assertEquals(user.getFirstName(), "maria");

		//progetto 0 , owner primo utente
		Project project = new Project("siw","esercitazione");
		project.setOwner(user);
		projectService.saveProject(project);
		assertEquals(user, project.getOwner());

		//progetto 1, owner primo utente
		Project project1 = new Project("siw1","esercitazione1");
		project1.setOwner(user);
		projectService.saveProject(project1);
		assertEquals(user, project1.getOwner());


		//condivisione progetto1 con secondo utente 
		User user2 = new User("lucab", "pwd", "luca", "bianchi");
		user2 = userService.saveUser(user2);
		project1 = projectService.shareProject(project1, user2);


		List<Project> ownerProjects = projectRepository.findByOwner(updateUser);
		assertEquals(ownerProjects.size(),2);
		
		List<Project> projectsVisibleByUser2 = projectRepository.findByMembers(user2);
		assertEquals( projectsVisibleByUser2.size(),1);

		List<User> userShared = userRepository.findByVisibleProjects(project1);
		assertEquals( userShared.size(),1);


	}
















}

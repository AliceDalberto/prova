package it.uniroma3.siw.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Task;
import it.uniroma3.siw.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	protected TaskRepository taskRepository;

	@Transactional
	public Task saveTask(Task task) {
		return this.taskRepository.save(task);
	}

	@Transactional
	public void deleteTask(Task task) {
		this.taskRepository.delete(task);
	}
	
	@Transactional
	public Task getTask(Long id) {
		Optional<Task> task = this.taskRepository.findById(id);
		return task.orElse(null);
		
	}
	
	@Transactional
	public Task setCompleted(Task task) {
		task.setCompleted(true);
		return this.taskRepository.save(task);
	}
}

package com.Reb.TaskManager;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
    return taskRepository.findAll();
    }   

    public Task createTask(Task task) {
    return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
    taskRepository.deleteById(id);
    }

    public Task getTaskById(Long id) {
    Optional<Task> task = taskRepository.findById(id);
    return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }

}
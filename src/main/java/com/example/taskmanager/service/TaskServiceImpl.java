package com.example.taskmanager.service;

import com.example.taskmanager.model.PriorityTask;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.StatusTask;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void createTask(Long id, Task task) {

        Project project = projectRepository.findById(id).get();

        Task taskNew = Task.builder()
                .title(task.title)
                .description(task.description)
                .status(StatusTask.NEW)
                .createDate(LocalDateTime.now())
                .priority(PriorityTask.NONE)
                .project(project)
                .build();

        taskRepository.save(taskNew);
    }

    @Override
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id).get();

        taskRepository.delete(task);
    }

    @Override
    public void updateTask(Long id, Task taskUpdate) {

        Task task = taskRepository.findById(id).get();

        task.setTitle(taskUpdate.getTitle());
        task.setDescription(taskUpdate.getDescription());
        task.setStatus(taskUpdate.getStatus());
        task.setPriority(task.getPriority());
        task.setDeadline(taskUpdate.getDeadline());

        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getTasks(Long id) {
        return taskRepository.findByProjectID(id);
    }
}

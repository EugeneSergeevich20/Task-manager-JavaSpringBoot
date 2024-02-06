package com.example.taskmanager.controller;

import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.ProjectServiceImpl;
import com.example.taskmanager.service.TaskServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task/")
public class TaskController {

    private final TaskServiceImpl taskService;
    private final ProjectServiceImpl projectService;

    public TaskController(TaskServiceImpl taskService, ProjectServiceImpl projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("{project_id}/add-task")
    public String addTaskPage(@PathVariable("project_id") Long id,
                              @ModelAttribute("task") Task task,
                              Model model){
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);

        return "task/add_task_page";
    }

    @PostMapping("add-task-process/{id}")
    public String addTaskToProject(@PathVariable("id") Long projectID,
                                   @ModelAttribute("task") Task task){

        taskService.createTask(projectID, task);

        return "redirect:/projects/" + projectID;
    }

}

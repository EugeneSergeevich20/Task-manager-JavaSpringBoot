package com.example.taskmanager.controller;

import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.ProjectServiceImpl;
import com.example.taskmanager.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects/")
public class ProjectController {

    private final ProjectServiceImpl projectService;
    private final UserServiceImpl userService;

    public ProjectController(ProjectServiceImpl projectService, UserServiceImpl userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("user-projects")
    public String projectsUserPage(Model model) {

        User user = userService.getAuthUser();

        if (user != null) {
            model.addAttribute("projects", projectService.getListProjectByUser(user));
            return "project/user_projects";
        }
        else {
            return "auth/login_page";
        }

    }

    @GetMapping("{id}")
    public String projectPage(@PathVariable("id") Long id, Model model){

        // для передачи информации на представление
        model.addAttribute("project", projectService.getProjectById(id));

        // для передачи информации о всех пользователях
        model.addAttribute("users", userService.getAllUsers());

        /* нужно для того, чтобы подключить нового пользователя к проекту
            (Передаётся пустой пользователь, в select выберается пользователь, передаёт для user_add значение ID
            , затем отправляется user_add только ID на сервер и из БД уже получаем User со всеми данными по ID)
         */
        //TODO: возможно придумать другой вариант выбора пользователя для подключения к проекту
        model.addAttribute("user_add", new User());

        return "project/project_page";
    }

    @GetMapping("add-project")
    public String addProjectPage(@ModelAttribute("project") Project project){
        return "project/add_project";
    }

    @PostMapping("add-project-process")
    public String addProjectProcess(@ModelAttribute("project") Project project){

        User user = userService.getAuthUser();

        projectService.createProject(project, user);

        return "redirect:/projects/user-projects";
    }

    @PostMapping("delete-process/{id}")
    public String deleteProject(@PathVariable("id") Long id){
        projectService.deleteProject(id);
        return "redirect:/projects/user-projects";
    }

    @PostMapping("edit-process/{id}")
    public String editProject(@PathVariable("id") Long id, @ModelAttribute("project") Project project){
        projectService.updateProject(id, project);
        return "redirect:/projects/" + id;
    }

    @PostMapping("add-user/{id}")
    public String addUserToProject(@PathVariable("id") Long id, @ModelAttribute("user_add") User user){

        User userAdd = userService.getUserById(user.getID());

        if (userAdd != null){
            projectService.addUserToProject(id, userAdd);
        }
        else {
            return "error/error_account"; // пока временная страница для ошибки
        }

        return "redirect:/projects/" + id;
    }
}

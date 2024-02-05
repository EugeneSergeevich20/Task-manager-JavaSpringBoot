package com.example.taskmanager.service;

import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createProject(Project project, User user) {

        Project projectNew = Project.builder()
                .createDate(LocalDateTime.now())
                .name(project.getName())
                .description(project.getDescription())
                .user(new ArrayList<>())
                .build();

        projectNew.user.add(user);

        projectRepository.save(projectNew);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public List<Project> getListProjectByUser(User user) {
        return projectRepository.findByUser(user);
    }

    @Override
    public void updateProject(Long id, Project projectUpdate) {

        Project projectOld = projectRepository.findById(id).get();

        projectOld.setName(projectUpdate.getName());
        projectOld.setDescription(projectUpdate.getDescription());

        projectRepository.save(projectOld);
    }

    /*TODO: Метод для подключение нового пользователя к проекту,
    *  пока тестовый вариант, чтобы проверить как будет добавляться в БД
    *  метод работает, вроде, норм, но скорее всего нужно будет менять
    *  под другой функционал для пользовательского интерфейса.
    *  Так же создать новый метод для UserService для получения пользователей к проекту
    *  (Сейчас передаются все пользователи, нужно исключить АДМИНОВ и тех кто уже подключен к проекту)
    * */
    @Override
    public void addUserToProject(Long id, User user) {

        Project project = projectRepository.findById(id).get();

        project.user.add(user);

        projectRepository.save(project);
    }
}

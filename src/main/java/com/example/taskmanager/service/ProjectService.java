package com.example.taskmanager.service;

import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.User;

import java.util.List;

public interface ProjectService {

    /***
     * Создание/добавление проекта
     * @param project
     * @param user
     */
    void createProject(Project project, User user);

    /***
     * Удаление проекта
     * @param id
     */
    void deleteProject(Long id);

    /***
     * Получение проекта по ID
     * @param id
     * @return
     */
    Project getProjectById(Long id);

    /***
     * Получение проектов у пользователя
     * @param user
     * @return
     */
    List<Project> getListProjectByUser(User user);

    /***
     * Редактирование проекта
     * @param id
     * @param projectUpdate
     */
    void updateProject(Long id, Project projectUpdate);

}

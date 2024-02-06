package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;

import java.util.List;

public interface TaskService {

    /***
     * Добавление навой задачи в проект
     * @param id ID - проекта
     * @param task
     */
    void createTask(Long id, Task task);

    /***
     * Удаление задачи
     * @param id
     */
    void deleteTask(Long id);

    /***
     * Обновление информации о задачи
     * @param id
     * @param taskUpdate
     */
    void updateTask(Long id, Task taskUpdate);

    /***
     * Получение задачи по ID
     * @param id
     * @return
     */
    Task getTaskById(Long id);

    /***
     * Получение всех задачи у проекта
     * @param id ID - проекта
     * @return
     */
    List<Task> getTasks(Long id);

}

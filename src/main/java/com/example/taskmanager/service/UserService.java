package com.example.taskmanager.service;

import com.example.taskmanager.model.User;

public interface UserService {

    /***
     * Метод регистрации пользователя
     * @param user
     */
    void register(User user);

    /***
     * Получение пользователя, который авторизован в приложение
     * @return
     */
    User getAuthUser();

    /***
     * Редактирование информации пользователя
     * @param userOld
     * @param userUpdate
     */
    void updateUser(User userOld, User userUpdate);

}

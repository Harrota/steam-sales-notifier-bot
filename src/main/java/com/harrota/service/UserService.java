package com.harrota.service;

import com.harrota.dao.UserDAO;
import com.harrota.model.App;
import com.harrota.model.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public UserService() {
    }

    public User findUser(Long id) {
        return userDAO.findById(id);
    }

    public void saveUser(User user) {
        userDAO.save(user);
    }

    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    public App findAppById(Long id) {
        return userDAO.findAppById(id);
    }

    public List<User> findUsersByChatId(Long chatId){
        return userDAO.findUsersByChatId(chatId);
    }



}
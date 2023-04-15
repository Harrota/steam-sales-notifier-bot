package com.harrota.service;

import com.harrota.dao.UserDao;
import com.harrota.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User save(User user){
        return userDao.save(user);
    }

    public Optional<User> findById(Long id){
        return userDao.findById(id);
    }

}

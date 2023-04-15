package com.harrota.service;

import com.harrota.dao.AppDao;
import com.harrota.entity.App;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppService {

    private final UserService userService;
    private final AppDao appDao;

    public AppService(UserService userService, AppDao appDao) {
        this.userService = userService;
        this.appDao = appDao;
    }

    public App save(App app){
        return appDao.save(app);
    }

    public Optional<App> findById(Long id){
        return appDao.findById(id);
    }
}

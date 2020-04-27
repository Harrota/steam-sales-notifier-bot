package com.harrota.service;

import com.harrota.dao.AppDAO;
import com.harrota.model.App;

import java.util.List;


public class AppService {
    private AppDAO appDAO = new AppDAO();

    public AppService() {
    }

    public App findApp(int id) {
        return appDAO.findById(id);
    }

    public void saveApp(App app) {
        appDAO.save(app);
    }

    public void deleteApp(App app) {
        appDAO.delete(app);
    }

    public void updateApp(App app) {
        appDAO.update(app);
    }

    public List<App> findAllApps() {
        return appDAO.findAll();
    }


}

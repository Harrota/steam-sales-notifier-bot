package com.harrota.service;

import com.harrota.dao.AppDAO;
import com.harrota.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {
    private AppDAO appDAO;

    @Autowired
    public void setAppDAO(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @Override
    @Transactional
    public List<App> allApps() {
        return appDAO.allApps();
    }

    @Override
    @Transactional
    public void add(App app) {
        appDAO.add(app);
    }

    @Override
    @Transactional
    public void delete(App app) {
        appDAO.delete(app);
    }

    @Override
    @Transactional
    public void edit(App app) {
        appDAO.edit(app);
    }

    @Override
    @Transactional
    public App getById(Long id) {
        return appDAO.getById(id);
    }
}

package com.harrota.dao;

import com.harrota.model.App;

import java.util.List;

public interface AppDAO {
    List<App> allApps();
    void add(App app);
    void delete(App app);
    void edit(App app);
    App getById(int id);
}

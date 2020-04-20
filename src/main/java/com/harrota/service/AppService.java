package com.harrota.service;

import com.harrota.model.App;

import java.util.List;

public interface AppService {
    List<App> allApps();
    void add(App app);
    void delete(App app);
    void edit(App app);
    App getById(Long id);
}

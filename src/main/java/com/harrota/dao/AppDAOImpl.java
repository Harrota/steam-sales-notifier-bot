package com.harrota.dao;

import com.harrota.model.App;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AppDAOImpl implements  AppDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<App> allApps() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from apps").list();
    }

    @Override
    public void add(App app) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(app);
    }

    @Override
    public void delete(App app) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(app);
    }

    @Override
    public void edit(App app) {
        Session session = sessionFactory.getCurrentSession();
        session.update(app);
    }

    @Override
    public App getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(App.class, id);
    }
}

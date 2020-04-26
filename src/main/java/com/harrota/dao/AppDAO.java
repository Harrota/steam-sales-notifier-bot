package com.harrota.dao;

import com.harrota.model.App;
import com.harrota.HibernateSessionFactoryUtil;
import com.harrota.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AppDAO {

    public App findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(App.class, id);
    }

    public void save(App app) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(app);
        tx1.commit();
        session.close();
    }

    public void update(App app) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(app);
        tx1.commit();
        session.close();
    }

    public void delete(App app) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(app);
        tx1.commit();
        session.close();
    }

    public User findUserById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public List<App> findAll() {
        List<App> apps = (List<App>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From App").list();
        return apps;
    }
}

package com.harrota.dao;

import com.harrota.HibernateSessionFactoryUtil;
import com.harrota.model.App;
import com.harrota.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDAO {


    public User findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }
    public App findAppById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        App app = session.get(App.class, id);
        session.close();
        return app;
    }

    public List<User> findUsersByChatId(Long chatId){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> list = criteria.add(Restrictions.eq("chatId", chatId)).list();
        session.close();
        return list;
    }

    public List<User> findAll() {
        List<User> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }
}
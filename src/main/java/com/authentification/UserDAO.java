package com.authentification;

import com.api.HibernateUtil;
import com.carEntity.Color;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 05.02.17.
 */
public class UserDAO {
    public void add(String userName,String password) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try{
            User user = new User(userName,password);
            session = HibernateUtil.getSessionFactory().openSession();
            tx =session.beginTransaction();
            if(!check(user)){
                session.saveOrUpdate(user);
                session.flush();
                session.clear();
                session.getTransaction().commit();
            }

        }catch (Exception e){
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error Insert", JOptionPane.OK_OPTION);
            tx.rollback();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public boolean check(User user){
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User " +
                    " WHERE userName =:name AND password  =:password")
                    .setString("name",user.getName())
                    .setString("password", user.getPassword());
            objects = query.list();
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  check contains", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        if (objects.size() != 0) return true;
        return false;
    }
}
/*
 void add(T t)throws SQLException;
    void update(Long l,T t)throws SQLException;
    T getById(Long l);
    void delete(T t);
    Collection getAll();
    void delete(Long l);
 */
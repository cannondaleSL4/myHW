package com.authentification.userDAO;

import com.api.HibernateUtil;
import com.authentification.userEntity.Role;
import com.authentification.userEntity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 05.02.17.
 */

/*
здесь перeгрузил метод add. пока не решил как верно делать add  - создавать объект сначала и загонять его сюда
или же передавать параметры, создавать объект уже здесь и тут его персистеть.
 */
public class UserDAOImpl implements UserDAO {
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

    public void add(User user) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try{
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

    public void update(User user) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx =session.beginTransaction();
            session.update(user);
            session.flush();
            session.clear();
            session.getTransaction().commit();
        }catch (Exception e){
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error Insert", JOptionPane.OK_OPTION);
            tx.rollback();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User ");
            objects = query.list();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return objects;
    }

    @Override
    public void delete(User user) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error while deleting", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Role getRole(String name){
        Session session = null;
        List<User> objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User " +
                    " WHERE user_name =:name")
                    //здесь то же пришлось применять имя колонки. тк. userName  не находит
                    .setString("name",name);
            objects = query.list();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  check contains", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return objects.get(0).getType();
    }

    public User getUser(String name){
        Session session = null;
        List<User> objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User " +
                    " WHERE user_name =:name")
                    //здесь то же пришлось применять имя колонки. тк. userName  не находит
                    .setString("name",name);
            objects = query.list();

            int a = 10;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  check contains", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return objects.get(0);
    }


    private boolean check(User user)throws SQLException{
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User " +
                    " WHERE user_name =:name")
                    //здесь то же пришлось применять имя колонки. тк. userName  не находит
                    .setString("name",user.getName());
            objects = query.list();
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

    public boolean check(String userName, String password)throws SQLException{
        User user = new User(userName,password);
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User " +
                    " WHERE user_name =:name AND user_password =:password")
                    //здесь то же пришлось применять имя колонки. тк. userName  не находит
                    .setString("name",user.getName())
                    .setString("password", user.getPassword());
            objects = query.list();
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

    public boolean checkUserName(String userName)throws SQLException{
        Session session = null;
        List<User> objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User " +
                    " WHERE user_name =:name")
                    //здесь то же пришлось применять имя колонки. тк. userName  не находит
                    .setString("name",userName);
            objects = query.list();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  check contains", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }

        for(User user:objects){
            if (userName.equalsIgnoreCase(user.getName())) return true;
        }
        return false;
    }
}
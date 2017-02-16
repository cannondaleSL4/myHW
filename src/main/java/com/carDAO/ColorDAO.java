package com.carDAO;

import com.api.HibernateUtil;
import org.hibernate.*;
import com.carEntity.Color;
import org.hibernate.Query;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 24.01.17.
 */
public class ColorDAO implements DAO<Color> {
    public void add(Color color) throws SQLException{
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx =session.beginTransaction();
            session.saveOrUpdate(color);
            session.flush();
            session.clear();
            session.getTransaction().commit();
        }catch (Exception e){
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error Color", JOptionPane.OK_OPTION);
            tx.rollback();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public boolean check(Color color){
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //какая то фигня поле класса isMetallic  не ищет, тут пришлось ставить имя таблицы
            Query query = session.createQuery("FROM Color " +
                    " WHERE colorName =:name AND is_metallic  =:metallic")
                    .setString("name",color.getColorName())
                    .setBoolean("metallic", color.isMetallic());
            objects = query.list();
            //session.getTransaction().commit();
        }catch (Exception e){
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  check contains", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        if (objects.size() != 0) return true;
        return false;
    }

    @Override
    public void update(Long l, Color color) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(getById(l));
           //session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for update", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public Color getById(Long l) {
        Session session = null;
        Color color = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            color = (Color) session.load(Color.class,l);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  getting by id", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return color;
    }

    @Override
    public void delete(Color color) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(color);
            session.getTransaction().commit();
        }catch (Exception e){
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error while deleting", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long l) {
        Session session = null;
        Color color =null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            color = (Color) session.load(Color.class,l);
            session.delete(color);
            session.getTransaction().commit();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List getAll() {
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Color ");
            objects = query.list();
            session.getTransaction().commit();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return objects;
    }
}

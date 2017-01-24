package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.CarParts;
import com.carEntity.Color;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by dima on 24.01.17.
 */
public class ColorDAO implements DAO<Color> {
    public void add(Color color) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(color);
            session.getTransaction().commit();
        }catch (Exception e){
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error Insert", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void update(Long l, Color color) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(getById(l));
            session.getTransaction().commit();
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
}

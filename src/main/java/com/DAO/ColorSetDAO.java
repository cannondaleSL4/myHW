package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.ColorSet;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by dima on 28.01.17.
 */
public class ColorSetDAO implements DAO<ColorSet> {
    @Override
    public void add(ColorSet colorSet) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.flush();
            session.saveOrUpdate(colorSet);
            session.clear();
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
    public void update(Long l, ColorSet colorSet) throws SQLException {

    }

    @Override
    public ColorSet getById(Long l) {
        Session session = null;
        ColorSet colorSet = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            colorSet = (ColorSet) session.get(ColorSet.class,l);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  getting by id", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return colorSet;
    }

    @Override
    public void delete(ColorSet colorSet) {

    }

    @Override
    public Collection getAll() {
        return null;
    }

    @Override
    public void delete(Long l) {

    }
}

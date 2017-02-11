package com.carDAO;

import com.api.HibernateUtil;
import com.carEntity.ColorSet;
import org.hibernate.*;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 28.01.17.
 */
public class ColorSetDAO implements DAO<ColorSet> {
    @Override
    public void add(ColorSet colorSet) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(colorSet);
            session.flush();
            session.clear();
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error ColorSet", JOptionPane.OK_OPTION);
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
    public List getAll() {
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("From ColorSet ");
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

    @Override
    public void delete(Long l) {

    }
}

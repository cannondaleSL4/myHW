package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.ColorSet;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by dima on 28.01.17.
 */
public class ColorSetDAO implements DAO<ColorSet> {
    @Override
    public void add(ColorSet colorSet) throws SQLException {
        System.out.println("here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(colorSet);
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
        return null;
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

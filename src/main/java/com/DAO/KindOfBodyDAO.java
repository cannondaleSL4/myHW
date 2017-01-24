package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.Color;
import com.carEntity.Engine;
import com.carEntity.KindOfBody;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by dima on 24.01.17.
 */
public class KindOfBodyDAO implements DAO <KindOfBody> {
    public void add(KindOfBody kindOfBody) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(kindOfBody);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Insert", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void update(Long l, KindOfBody kindOfBody) throws SQLException {
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
    public KindOfBody getById(Long l) {
        Session session = null;
        KindOfBody kindOfBody = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            kindOfBody = (KindOfBody) session.load(KindOfBody.class,l);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  getting by id", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return kindOfBody;
    }
}

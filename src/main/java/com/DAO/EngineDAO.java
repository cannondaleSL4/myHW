package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.Color;
import com.carEntity.Engine;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by dima on 24.01.17.
 */
public class EngineDAO implements DAO <Engine> {
    public void add(Engine engine) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(engine);
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
    public void update(Long l, Engine engine) throws SQLException {
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
    public Engine getById(Long l) {
        Session session = null;
        Engine engine = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            engine = (Engine) session.load(Engine.class,l);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  getting by id", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return engine;
    }
}

package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.Engine;
import com.carEntity.Transmission;
import org.hibernate.*;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 24.01.17.
 */
public class TransmissionDAO implements DAO <Transmission> {
    public void add(Transmission transmission) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            if (!check(transmission)){
                session.saveOrUpdate(transmission);
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

    public boolean check(Transmission transmission){
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Transmission " +
                    " WHERE transmissionName =:name AND  numberOfSpeed  =:numofspeed")
                    .setString("name",transmission.getTransmissionName())
                    .setInteger("numofspeed",transmission.getNumberOfSpeed());
            objects = query.list();
            System.out.println(objects);
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

    @Override
    public void update(Long l, Transmission transmission) throws SQLException {
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
    public Transmission getById(Long l) {
        Session session = null;
        Transmission transmission = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transmission = (Transmission) session.load(Transmission.class,l);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  getting by id", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return transmission;
    }

    @Override
    public void delete(Transmission transmission) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(transmission);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error while deleting", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long l) {
        Session session = null;
        Transmission transmission =null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            transmission = (Transmission) session.load(Transmission.class,l);
            session.delete(transmission);
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
            Query query = session.createQuery("FROM Transmission ");
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

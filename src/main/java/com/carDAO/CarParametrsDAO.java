package com.carDAO;

import com.api.HibernateUtil;
import com.carEntity.CarParametrs;
import org.hibernate.*;
import org.hibernate.Session;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 25.01.17.
 */
public class CarParametrsDAO implements DAO <CarParametrs> {
    @Override
    public void add(CarParametrs carParametrs) throws SQLException{
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx =session.beginTransaction();
            session.saveOrUpdate(carParametrs);
            session.flush();
            session.clear();
            session.getTransaction().commit();
        }catch (Exception e){
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error CarParametrs", JOptionPane.OK_OPTION);
            tx.rollback();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void update(Long l, CarParametrs carParametrs) throws SQLException {
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
    public CarParametrs getById(Long l) {
        Session session = null;
        CarParametrs carParametrs = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            carParametrs = (CarParametrs) session.load(CarParametrs.class,l);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  getting by id", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return carParametrs;
    }

    @Override
    public void delete(CarParametrs carParametrs) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(carParametrs);
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
        CarParametrs carParametrs =null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            carParametrs = (CarParametrs) session.load(CarParametrs.class,l);
            session.delete(carParametrs);
            session.getTransaction().commit();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error CarParametrs", JOptionPane.OK_OPTION);
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
            Query query = session.createQuery("FROM CarParametrs ");
            objects = query.list();
            //session.getTransaction().commit();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return objects;
    }

    public List getByModel(Long modelid) {
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM CarParametrs WHERE ModelName.id = 1");
            objects = query.list();
            //session.getTransaction().commit();
        }catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return objects;
    }
}

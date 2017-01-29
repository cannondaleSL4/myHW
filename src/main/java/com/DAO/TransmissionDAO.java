package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.Color;
import com.carEntity.Engine;
import com.carEntity.KindOfBody;
import com.carEntity.Transmission;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dima on 24.01.17.
 */
public class TransmissionDAO implements DAO <Transmission> {
    public void add(Transmission transmission) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(transmission);
            session.flush();
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
    public Collection getAll() {
        Session session = null;
        List transmission  = new ArrayList<Transmission>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Transmission> criteria = builder.createQuery(Transmission.class);
            criteria.from(Transmission.class);
            transmission = session.createQuery(criteria).getResultList();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return transmission;
    }
}

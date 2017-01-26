package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.Color;
import com.carEntity.KindOfBody;
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
public class KindOfBodyDAO implements DAO <KindOfBody> {
    public void add(KindOfBody kindOfBody) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(kindOfBody);
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

    @Override
    public void delete(KindOfBody kindOfBody) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(kindOfBody);
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
        KindOfBody kindOfBody =null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            kindOfBody = (KindOfBody) session.load(KindOfBody.class,l);
            session.delete(kindOfBody);
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
        List bodies  = new ArrayList<KindOfBody>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<KindOfBody> criteria = builder.createQuery(KindOfBody.class);
            criteria.from(KindOfBody.class);
            bodies = session.createQuery(criteria).getResultList();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return bodies;
    }
}

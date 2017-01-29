package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.CarParametrs;
import com.carEntity.Color;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dima on 25.01.17.
 */
public class CarParametrsDAO implements DAO <CarParametrs> {
    @Override
    public void add(CarParametrs carParametrs) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(carParametrs);
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
        List carParametrs  = new ArrayList<CarParametrs>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Color> criteria = builder.createQuery(Color.class);
            criteria.from(Color.class);
            carParametrs = session.createQuery(criteria).getResultList();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return carParametrs;
    }
}

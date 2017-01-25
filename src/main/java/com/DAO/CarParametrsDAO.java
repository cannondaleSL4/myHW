package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.CarParametrs;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by dima on 25.01.17.
 */
public class CarParametrsDAO implements DAO <CarParametrs> {
    @Override
    public void add(CarParametrs carParametrs) throws SQLException {
        System.out.println("here");
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(carParametrs);
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
    public void update(Long l, CarParametrs carParametrs) throws SQLException {

    }

    @Override
    public CarParametrs getById(Long l) {
        return null;
    }

    @Override
    public void delete(CarParametrs carParametrs) {

    }

    @Override
    public Collection getAll() {
        return null;
    }

    @Override
    public void delete(Long l) {

    }
}

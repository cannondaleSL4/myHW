package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.Color;
import com.carEntity.Engine;
import com.carEntity.ModelName;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by dima on 24.01.17.
 */
public class ModelNameDAO implements DAO <ModelName> {
    public void add(ModelName modelName) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(modelName);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Insert", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }
}

package com.carDAO;

import com.api.HibernateUtil;
import com.carEntity.KindOfBody;
import org.hibernate.*;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 24.01.17.
 */
public class KindOfBodyDAO implements DAO <KindOfBody> {
    public void add(KindOfBody kindOfBody) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            if (!check(kindOfBody)){
                session.saveOrUpdate(kindOfBody);
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

    public boolean check(KindOfBody kindOfBody){
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM KindOfBody " +
                    " WHERE nameKindOfBody =:name")
                    .setString("name",kindOfBody.getNameKindOfBody());
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
    public List getAll() {
        Session session = null;
        List objects  = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM KindOfBody ");
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

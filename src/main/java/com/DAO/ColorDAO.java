package com.DAO;

import com.api.HibernateUtil;
import com.carEntity.Color;
import org.hibernate.Session;
import org.hibernate.query.Query;


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
public class ColorDAO implements DAO<Color> {
    public void add(Color color) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //check(color);
            session.saveOrUpdate(color);
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

    boolean check(Color color){
        boolean isitHere = false;
        Session session = null;
        String sql = "FROM color_table WHERE color_table_name = " + color.getColorName() +"+ AND is_metallic = "+ color.isMetallic()+";";
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            /*Query query = session.createQuery("from color_table where color_table_name = :name and is_metallic = :metallic ");
            query.setParameter("name",color.getColorName());
            query.setParameter("metallic", color.isMetallic());
            int result = query.getFirstResult();
            System.out.println("dsdsdsdsdsdsd " + result);*/
            Query query = session.createQuery(sql);
            List results = query.list();
            System.out.println(results.size());
        }catch (Exception e){
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error Insert", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return isitHere;
    }

    @Override
    public void update(Long l, Color color) throws SQLException {
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
    public Color getById(Long l) {
        Session session = null;
        Color color = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            color = (Color) session.load(Color.class,l);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error for  getting by id", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return color;
    }

    @Override
    public void delete(Color color) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(color);
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
        Color color =null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            color = (Color) session.load(Color.class,l);
            session.delete(color);
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
        List colors  = new ArrayList<Color>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Color> criteria = builder.createQuery(Color.class);
            criteria.from(Color.class);
            colors = session.createQuery(criteria).getResultList();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return colors;
    }
}

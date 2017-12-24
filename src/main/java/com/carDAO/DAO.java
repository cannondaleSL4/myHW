package com.carDAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by dima on 24.01.17.
 */
public interface DAO <T> {
    void add(T t)throws SQLException;
    void update(Integer l,T t)throws SQLException;
    T getById(Integer l);
    void delete(T t);
    Collection getAll();
    void delete(Integer l);
}

package com.DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by dima on 24.01.17.
 */
public interface DAO <T> {
    void add(T t)throws SQLException;
    void update(Long l,T t)throws SQLException;
    T getById(Long l);
    void delete(T t);
    Collection getAll();
    void delete(Long l);
}

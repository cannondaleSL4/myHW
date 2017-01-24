package com.DAO;

import java.sql.SQLException;

/**
 * Created by dima on 24.01.17.
 */
public interface DAO <CarParts> {
    void add(CarParts carParts)throws SQLException;
}

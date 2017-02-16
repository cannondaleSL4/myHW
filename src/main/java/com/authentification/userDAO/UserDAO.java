package com.authentification.userDAO;

import com.authentification.userEntity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 07.02.17.
 */
public interface UserDAO {
    public void add(String name, String pass)throws SQLException;
    public List<User> getAll()throws SQLException ;
    public void delete(User user)throws SQLException ;
}

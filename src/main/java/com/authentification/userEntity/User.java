package com.authentification.userEntity;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;
import java.io.Serializable;
import java.security.Principal;

/**
 * Created by dima on 04.02.17.
 */
@Entity
/*
нельзя называть таблицу user это резервированное слово sql
 */
@Table(name = "user_base")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class User implements Serializable, Principal {
    private Long id;
    private String userName;
    private String password;
    //т.е. по умолчанию роль - юзер.
    private Role type = Role.USER;
    private String salt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";

    public User(){}

    public User(String userName, String password){
        this.userName = userName;
        this.password = DigestUtils.md5Hex(password + salt);
    }

    public User(String userName, String password,Role type){
        this.userName = userName;
        this.password = DigestUtils.md5Hex(password + salt);
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_user",nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "user_name",nullable = false, unique = true)
    public String getName() {
        return userName;
    }

    @Column(name = "user_password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Enumerated(EnumType.STRING)
    public Role getType() {
        return type;
    }

    public void setType(Role type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password+ salt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        if (!password.equals(user.password)) return false;
        return type == user.type;

    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
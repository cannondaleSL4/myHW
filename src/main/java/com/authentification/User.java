package com.authentification;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dima on 04.02.17.
 */
@Entity
/*
нельзя называть таблицу user это резервированное слово sql
 */
@Table(name = "user_base")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class User implements Serializable {
    private Long id;
    private String userName;
    private String password;
    //т.е. по умолчанию роль - юзер.
    private Role type = Role.USER;

    public User(){}

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password,Role type){
        this.userName = userName;
        this.password = password;
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
        this.password = password;
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
}

/*
@Entity
@Table (
        name  = "color_table",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"color_table_name","is_metallic"})
)
@JsonTypeInfo (use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Color implements CarParts, Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String colorName;
    private boolean isMetallic;
    //private ColorSet colorSet = new ColorSet();
    private Set<ColorSet> colorSet = new HashSet<ColorSet>(0);

    public Color(){}

    public Color(String colorName, boolean isMetallic) {
        this.colorName = colorName;
        this.isMetallic = isMetallic;
    }

    @Id
    @Column(name = "idcolor_table")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "color_table_name", nullable = false)
    public String getColorName() {
        return colorName;
    }

 */
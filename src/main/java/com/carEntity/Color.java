package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table (
        name  = "color_table",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"color_table_name","is_metallic"})
)
@JsonTypeInfo (use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Color implements CarParts, Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private String colorName;
    private boolean isMetallic;
    private ColorSet colorSet = new ColorSet();

    public Color(){}

    public Color(String colorName, boolean isMetallic) {
        this.colorName = colorName;
        this.isMetallic = isMetallic;
    }

    @Id
    @Column(name = "idcolor_table")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "color_table_name", nullable = false)
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Column(name = "is_metallic", nullable = false)
    public boolean isMetallic() {
        return isMetallic;
    }

    public void setMetallic(boolean metallic) {
        isMetallic = metallic;
    }

    /*@ManyToOne(fetch = FetchType.LAZY)
    public ColorSet getColorSet() {
        return colorSet;
    }

    public void setColorSet(ColorSet colorSet) {
        this.colorSet = colorSet;
    }
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        if (isMetallic != color.isMetallic) return false;
        return colorName.equals(color.colorName);

    }

    @Override
    public int hashCode() {
        int result = colorName.hashCode();
        result = 31 * result + (isMetallic ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Color{" +
                "id=" + id +
                ", colorName='" + colorName + '\'' +
                ", isMetallic=" + isMetallic +
                '}';
    }
}
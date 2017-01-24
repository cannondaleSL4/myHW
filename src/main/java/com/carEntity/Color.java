package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table (
        name  = "color_table",
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"color_table_name","is_metallic"})
)
/*@Table(
        name="color_table",
        uniqueConstraints = { @UniqueConstraint(columnNames =
                { "color_table_name", "is_metallic" }) })*/
/*@SQLInsert(sql = "INSERT INTO color_table (idcolor_table,color_table_name, is_metallic) VALUES (?,?,?)" +
        " on CONFLICT do nothing;")*/
@SQLInsert(callable = true,sql = "INSERT INTO color_table (color_table_name, is_metallic) VALUES (?,?)" +
        " on conflict do nothing ;")
@JsonTypeInfo (use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Color implements CarParts {

    private int id;
    private String colorName;
    private boolean isMetallic;

    public Color(){}

    public Color(String colorName, boolean isMetallic) {
        this.colorName = colorName;
        this.isMetallic = isMetallic;
    }

    @Id
    @Column(name = "idcolor_table")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //@GenericGenerator(name="gen",strategy="increment")
    //@GeneratedValue(generator="gen")

    //@GeneratedValue(strategy = GenerationType.AUTO)
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
}

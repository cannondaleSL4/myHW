package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


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

    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String colorName;
    private Boolean isMetallic;
    private String imgAdress;
    private Set<ColorSet> colorSet = new HashSet<ColorSet>(0);

    public Color(){}

    public Color(String colorName, boolean isMetallic,String imgAdress) {
        this.colorName = colorName;
        this.isMetallic = isMetallic;
        this.imgAdress = imgAdress;
    }

    @Id
    @Column(name = "idcolor_table")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public Boolean isMetallic() {
        return isMetallic;
    }

    @Column(name = "imgAdress", nullable = false)
    public String getImgAdress() {
        return imgAdress;
    }

    public void setMetallic(boolean metallic) {
        isMetallic = metallic;
    }

    @ManyToMany(mappedBy = "colors", fetch = FetchType.EAGER)
    public Set<ColorSet> getColors() {
        return colorSet;
    }

    public void setColors(Set<ColorSet> colorSet) {
        this.colorSet = colorSet;
    }

    public void setImgAdress(String imgAdress) {
        this.imgAdress = imgAdress;
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

    @Override
    public String toString() {
        return "Color{" +
                "id=" + id +
                ", colorName='" + colorName + '\'' +
                ", isMetallic=" + isMetallic +
                ", colorSet=" + colorSet +
                '}';
    }
}
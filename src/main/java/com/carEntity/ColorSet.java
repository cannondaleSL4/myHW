package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dima on 02.01.17.
 */
@Entity
@Table(name ="color_set")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class ColorSet implements CarParts {

    private Long id;

    private CarParametrs carParametrs;
    private Set<Color> colors = new HashSet<Color>();

    public ColorSet() {
    }

    public ColorSet(Long idColorSet){
        this.id = idColorSet;
    }

    @Id
    @Column(name = "idcolor_set")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdColorSet() {
        return id;
    }

    public void setIdColorSet(Long idColorSet) {
        this.id = idColorSet;
    }

    public void addColor(Color color){
        colors.add(color);
    }


    @OneToMany(mappedBy = "colorSet")
    //@JoinColumn(name = "idcolor_table", nullable = false)
    public Set<Color> getColors() {
        return colors;
    }

    @OneToOne(mappedBy = "colorSet",cascade = CascadeType.ALL)
    public CarParametrs getCarParametrs() {
        return carParametrs;
    }

    public void setCarParametrs(CarParametrs carParametrs) {
        this.carParametrs = carParametrs;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }
}

/*
@Entity
@Table(name="engine")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Engine implements CarParts {
    private Long id;
    private String nameOfEngine;
    private int horsepower;


    public Engine(){}

    public Engine(String nameOfEngine) {
        this.nameOfEngine = nameOfEngine;
    }

    public Engine(String nameOfEngine, int horsepower) {
        this.nameOfEngine = nameOfEngine;
        this.horsepower = horsepower;
    }

    public Engine(String nameOfEngine, int horsepower,Long id) {
        this.nameOfEngine = nameOfEngine;
        this.horsepower = horsepower;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idengine")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "engine_name",nullable = false, unique = true)
    public String getNameOfEngine() {
        return nameOfEngine;
    }

    public void setNameOfEngine(String nameOfEngine) {
        this.nameOfEngine = nameOfEngine;
    }

    @Column(name = "horsepower")
    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "nameOfEngine='" + nameOfEngine + '\'' +
                ", horsepower=" + horsepower +
                "}";
    }

    @OneToOne(mappedBy = "engine",cascade = CascadeType.ALL)
    private CarParametrs carParametrs;
}

 */
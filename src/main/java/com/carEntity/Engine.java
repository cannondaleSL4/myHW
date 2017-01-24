package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;

/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table(name="engine")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Engine implements CarParts {
    private Long id;
    private String nameOfEngine;
    private int horsepower;


    public Engine(String nameOfEngine, int horsepower,Long id) {
        this.nameOfEngine = nameOfEngine;
        this.horsepower = horsepower;
        this.id = id;
    }

    public Engine(){}

    public Engine(String nameOfEngine) {
        this.nameOfEngine = nameOfEngine;
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

    @Column(name = "engine_name",nullable = false)
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
}

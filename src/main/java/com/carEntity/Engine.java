package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table(name="engine")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Engine implements CarParts, Serializable {

    private static final long serialVersionUID = 1L;

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

    public Engine(Long id,String nameOfEngine, int horsepower) {
        this.nameOfEngine = nameOfEngine;
        this.horsepower = horsepower;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idengine",nullable = false, unique = true)
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

    @Column(name = "horsepower",nullable = false, unique = true)
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

package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;

/**
 * Created by dima on 13.12.16.
 */

/*
ПОСЛЕ ДОБАВЛЕНИЯ НАБОРА ЦВЕТОВ ОБЯЗАТЕЛЬНО ДОПОЛНИТЬ СПИСОК UNICKE!!!!!!!!
 */

@Entity
@Table(name ="car_parm")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class CarParametrs implements CarParts  {

    private Long id;

    @Id
    @Column(name = "id_car_parm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idengine")
    private Engine engine;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private KindOfBody kindOfBody;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ModelName modelName;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Transmission transmission;

    public CarParametrs() { }

    public CarParametrs(Engine engine,KindOfBody kindOfBody,
                        ModelName modelName, Transmission transmission){
        this.engine = engine;
        this.kindOfBody = kindOfBody;
        this.modelName = modelName;
        this.transmission = transmission;
    }
}

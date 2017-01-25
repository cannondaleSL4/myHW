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

    public CarParametrs() { }

    public CarParametrs(Engine engine,KindOfBody kindOfBody,
                        ModelName modelName, Transmission transmission){
        this.engine = engine;
        this.kindOfBody = kindOfBody;
        this.modelName = modelName;
        this.transmission = transmission;
    }

    @Id
    @Column(name = "id_car_parm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
    вот здесь херня!
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Engine engine;

    @OneToOne(cascade = CascadeType.ALL)
    private KindOfBody kindOfBody;

    @OneToOne(cascade = CascadeType.ALL)
    private ModelName modelName;

    @OneToOne(cascade = CascadeType.ALL)
    private Transmission transmission;

    public Engine getEngine() {
        return engine;
    }

    public KindOfBody getKindOfBody() {
        return kindOfBody;
    }

    public ModelName getModelName() {
        return modelName;
    }

    public Transmission getTransmission() {
        return transmission;
    }
}

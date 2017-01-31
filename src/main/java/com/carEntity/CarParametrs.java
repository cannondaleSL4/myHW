package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by dima on 13.12.16.
 */

/*
ПОСЛЕ ДОБАВЛЕНИЯ НАБОРА ЦВЕТОВ ОБЯЗАТЕЛЬНО ДОПОЛНИТЬ СПИСОК UNICKE!!!!!!!!
 */

@Entity
@Table(name ="car_parm")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class CarParametrs implements CarParts, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Engine engine;
    private KindOfBody kindOfBody;
    private ModelName modelName;
    private Transmission transmission;
    private ColorSet colorSet;

    public CarParametrs() { }

    public CarParametrs(Engine engine,KindOfBody kindOfBody,
                        ModelName modelName, Transmission transmission,ColorSet colorSet){
        this.engine = engine;
        this.kindOfBody = kindOfBody;
        this.modelName = modelName;
        this.transmission = transmission;
        this.colorSet = colorSet;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idengine")
    public Engine getEngine() {
        return engine;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idkind_of_body")
    public KindOfBody getKindOfBody() {
        return kindOfBody;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcurrent_model")
    public ModelName getModelName() {
        return modelName;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idtransmission")
    public Transmission getTransmission() {
        return transmission;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcolor_set")
    public ColorSet getColorSet() {
        return colorSet;
    }

    public void setColorSet(ColorSet colorSet) {
        this.colorSet = colorSet;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setKindOfBody(KindOfBody kindOfBody) {
        this.kindOfBody = kindOfBody;
    }

    public void setModelName(ModelName modelName) {
        this.modelName = modelName;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
}

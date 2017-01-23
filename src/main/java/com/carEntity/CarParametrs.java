package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;

/**
 * Created by dima on 13.12.16.
 */

@Entity
@Table(name ="car_parm")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class CarParametrs implements CarParts  {

    private int id;


    public CarParametrs(int id) {
        this.id = id;
    }

    public CarParametrs() {

    }

    @Id
    @Column(name = "id_car_parm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ModelName modelName;

    @Column(name= "model_name")
    public ModelName getModelName() {
        return modelName;
    }

    public void setModelName(ModelName modelName) {
        this.modelName = modelName;
    }*/
}

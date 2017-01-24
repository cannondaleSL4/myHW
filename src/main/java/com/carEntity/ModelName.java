package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;

/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table(name ="current_model")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class ModelName implements CarParts {

    private Long id;
    private String modelName;

    public ModelName(String modelName) {
        this.modelName = modelName;
    }

    public ModelName(){}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idcurrent_model")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name= "current_modelcol")
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}

package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table(name ="current_model")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class ModelName implements CarParts, Serializable {


    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String modelName;
    private String imgAdress;

    public ModelName(String modelName, String imgAdress) {
        this.modelName = modelName;
        this.imgAdress = imgAdress;
    }

    public ModelName(){}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idcurrent_model",nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    @Column(name= "current_modelcol",nullable = false ,unique = true)
    public String getModelName() {
        return modelName;
    }

    @Column(name= "model_img",nullable = false ,unique = true)
    public String getImgAdress() {
        return imgAdress;
    }

    public void setImgAdress(String imgAdress) {
        this.imgAdress = imgAdress;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "current_model",cascade = CascadeType.REMOVE)
    private CarParametrs carParametrs;
}

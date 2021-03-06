package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table(name ="transmission")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Transmission implements CarParts, Serializable {


    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String transmissionName;
    private int numberOfSpeed;

    public Transmission(String transmissionName, int numberOfSpeed) {
        this.transmissionName = transmissionName;
        this.numberOfSpeed = numberOfSpeed;
    }

    public Transmission(){}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idtransmission",nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "transmission_name",nullable = false, unique = true)
    public String getTransmissionName() {
        return transmissionName;
    }

    public void setTransmissionName(String transmissionName) {
        this.transmissionName = transmissionName;
    }

    @Column(name = "number_of_speed",nullable = false)
    public int getNumberOfSpeed() {
        return numberOfSpeed;
    }

    public void setNumberOfSpeed(int numberOfSpeed) {
        this.numberOfSpeed = numberOfSpeed;
    }

    @OneToOne(mappedBy = "transmission",cascade = CascadeType.REMOVE)
    private CarParametrs carParametrs;
}

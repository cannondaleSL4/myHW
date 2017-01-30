package com.carEntity;

//import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table(name ="transmission")
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Transmission implements CarParts {

    private Long id;
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "transmission_name",nullable = false, unique = true)
    public String getTransmissionName() {
        return transmissionName;
    }

    public void setTransmissionName(String transmissionName) {
        this.transmissionName = transmissionName;
    }

    @Column(name = "number_of_speed",nullable = false, unique = true)
    public int getNumberOfSpeed() {
        return numberOfSpeed;
    }

    public void setNumberOfSpeed(int numberOfSpeed) {
        this.numberOfSpeed = numberOfSpeed;
    }

    @OneToOne(mappedBy = "transmission",cascade = CascadeType.REMOVE)
    private CarParametrs carParametrs;
}

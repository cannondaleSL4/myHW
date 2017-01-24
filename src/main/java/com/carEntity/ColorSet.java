package com.carEntity;

import javax.persistence.*;

/**
 * Created by dima on 02.01.17.
 */
@Entity
@Table(name ="color_set_number")
public class ColorSet implements CarParts {

    /*@Id
    @Column(name = "idcolor_table")
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/

    @Id
    @Column(name = "idcolor_set")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ColorSet(Long idColorSet){
        this.id = idColorSet;
    }

    public ColorSet() {
    }

    public Long getIdColorSet() {
        return id;
    }

    public void setIdColorSet(Long idColorSet) {
        this.id = idColorSet;
    }

    @Override
    public String toString() {
        return "ColorSet{" +
                "idColorSet=" + id +
                '}';
    }
}

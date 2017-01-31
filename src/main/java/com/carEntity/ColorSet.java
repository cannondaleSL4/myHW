package com.carEntity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name ="color_set")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class ColorSet implements CarParts, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private CarParametrs carParametrs;
    private Set<Color> colors = new HashSet<Color>();

    public ColorSet() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcolor_set")
    public Long getIdColorSet() {
        return id;
    }

    public void setIdColorSet(Long idColorSet) {
        this.id = idColorSet;
    }

    public void addColor(Color color){
        color.setColorSet(this);
        colors.add(color);
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "colorSet")
    public Set<Color> getColors() {
        return colors;
    }

    @OneToOne(mappedBy = "colorSet",cascade = CascadeType.ALL)
    public CarParametrs getCarParametrs() {
        return carParametrs;
    }

    public void setCarParametrs(CarParametrs carParametrs) {
        this.carParametrs = carParametrs;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }
}
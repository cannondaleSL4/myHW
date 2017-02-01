package com.carEntity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.annotations.Cascade;

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
    //private CarParametrs carParametrs;
    private Set<Color> colors = new HashSet<Color>(0);

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
        colors.add(color);
        color.addColorSet(this);
    }

    @JsonIgnore
    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "option_of_color",
            joinColumns = @JoinColumn(name = "idcolor_set"),
            inverseJoinColumns = @JoinColumn(name = "idcolor_table")
    )
    private Set<Color> getColors() {
        return colors;
    }

    /*@OneToOne(mappedBy = "colorSet",cascade = CascadeType.ALL)
    public CarParametrs getCarParametrs() {
        return carParametrs;
    }*/

    /*public void setCarParametrs(CarParametrs carParametrs) {
        this.carParametrs = carParametrs;
    }*/

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "ColorSet{" +
                "id=" + id +
                ", colors=" + colors +
                '}';
    }
}
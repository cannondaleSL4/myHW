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
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "option_of_color",
            joinColumns = @JoinColumn(name = "idcolor_set", referencedColumnName = "idcolor_set"),
            inverseJoinColumns = @JoinColumn(name = "idcolor_table",referencedColumnName = "idcolor_table")
    )
    public Set<Color> getColors() {
        return colors;
    }

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

    @OneToOne(mappedBy = "engine",cascade = CascadeType.ALL)
    private CarParametrs carParametrs;
}
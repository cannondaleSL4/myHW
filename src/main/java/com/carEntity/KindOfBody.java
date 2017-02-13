package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table(name ="kind_of_body")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class KindOfBody implements CarParts, Serializable {


    private static final long serialVersionUID = 1L;

    private Long id;
    private String nameKindOfBody;
    private String imgAdress;

    public KindOfBody(String nameKindOfBody,String imgAdress) {
        this.nameKindOfBody = nameKindOfBody;
        this.imgAdress = imgAdress;
    }

    public KindOfBody(){}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idkind_of_body",nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    @Column(name="name_of_kind_of_body",nullable = false, unique = true)
    public String getNameKindOfBody() {
        return nameKindOfBody;
    }

    @Column(name="imgAdress", unique = true)
    public String getImgAdress() {
        return imgAdress;
    }

    public void setImgAdress(String imgAdress) {
        this.imgAdress = imgAdress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameKindOfBody(String nameKindOfBody) {
        this.nameKindOfBody = nameKindOfBody;
    }

    @OneToOne(mappedBy = "kindOfBody",cascade = CascadeType.REMOVE)
    private CarParametrs carParametrs;
}

package com.carEntity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import javax.persistence.*;

/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table(name ="kind_of_body")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class KindOfBody implements CarParts {

    private Long id;
    private String nameKindOfBody;

    public KindOfBody(String nameKindOfBody) {
        this.nameKindOfBody = nameKindOfBody;
    }

    public KindOfBody(){}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idkind_of_body")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name_of_kind_of_body",nullable = false)
    public String getNameKindOfBody() {
        return nameKindOfBody;
    }

    public void setNameKindOfBody(String nameKindOfBody) {
        this.nameKindOfBody = nameKindOfBody;
    }
}

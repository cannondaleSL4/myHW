package com.carEntity;


import javafx.util.Pair;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dima on 08.12.16.
 */
@Entity
@Table(name ="color_table_has_color_set")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class ColotTableWithSetNumber {
    
    private Set<Color> colors;

    private int colorSetId;
    private int colorId;

    public ColotTableWithSetNumber(Collection<? extends Color> color){
        this.colors = new HashSet<Color>(color);
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public int getColorSetId() {
        return colorSetId;
    }

    public void setColorSetId(int colorSetId) {
        this.colorSetId = colorSetId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}

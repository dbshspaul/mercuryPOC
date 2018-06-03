package com.jactravel.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/*
*created by Debashis Paul on 03-Jun-18 7:44 PM
*/
@Table("property")
public class Property {
    @PrimaryKey
    private Integer propertyId;
    private String propertyRating;
    private String name;
    private String roomType;
    @JsonIgnore
    @Transient
    private boolean isSync;

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyRating() {
        return propertyRating;
    }

    public void setPropertyRating(String propertyRating) {
        this.propertyRating = propertyRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}

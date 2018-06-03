package com.jactravel.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/*
*created by Debashis Paul on 03-Jun-18 7:47 PM
*/
@Table("property_room_type")
public class PropertyRoomType {
    private String room;
    private Integer maxAdults;
    private Integer maxAdultsWithChildren;
    private Integer autoPromoteChildren;
    private Integer maxChildren;
    private Integer infants;
    private Integer infantsOccupancy;
    private Integer extraBedTypeId;
    private Integer minOcc;
    private Integer stdOcc;
    private Integer maxOcc;
    private Integer roomType;
    private Integer youthsCountAsAdults;
    private Integer youth;
    private Integer roomView;
    private Integer propertyId;
    @PrimaryKey
    private Integer roomTypeId;
    private Integer mealBasisId;
    private Integer childAgeTo;
    private Integer youthAgeFrom;
    private Integer youthAgeTo;
    @JsonIgnore
    @Transient
    private boolean isSync;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(Integer maxAdults) {
        this.maxAdults = maxAdults;
    }

    public Integer getMaxAdultsWithChildren() {
        return maxAdultsWithChildren;
    }

    public void setMaxAdultsWithChildren(Integer maxAdultsWithChildren) {
        this.maxAdultsWithChildren = maxAdultsWithChildren;
    }

    public Integer getAutoPromoteChildren() {
        return autoPromoteChildren;
    }

    public void setAutoPromoteChildren(Integer autoPromoteChildren) {
        this.autoPromoteChildren = autoPromoteChildren;
    }

    public Integer getMaxChildren() {
        return maxChildren;
    }

    public void setMaxChildren(Integer maxChildren) {
        this.maxChildren = maxChildren;
    }

    public Integer getInfants() {
        return infants;
    }

    public void setInfants(Integer infants) {
        this.infants = infants;
    }

    public Integer getInfantsOccupancy() {
        return infantsOccupancy;
    }

    public void setInfantsOccupancy(Integer infantsOccupancy) {
        this.infantsOccupancy = infantsOccupancy;
    }

    public Integer getExtraBedTypeId() {
        return extraBedTypeId;
    }

    public void setExtraBedTypeId(Integer extraBedTypeId) {
        this.extraBedTypeId = extraBedTypeId;
    }

    public Integer getMinOcc() {
        return minOcc;
    }

    public void setMinOcc(Integer minOcc) {
        this.minOcc = minOcc;
    }

    public Integer getStdOcc() {
        return stdOcc;
    }

    public void setStdOcc(Integer stdOcc) {
        this.stdOcc = stdOcc;
    }

    public Integer getMaxOcc() {
        return maxOcc;
    }

    public void setMaxOcc(Integer maxOcc) {
        this.maxOcc = maxOcc;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Integer getYouthsCountAsAdults() {
        return youthsCountAsAdults;
    }

    public void setYouthsCountAsAdults(Integer youthsCountAsAdults) {
        this.youthsCountAsAdults = youthsCountAsAdults;
    }

    public Integer getYouth() {
        return youth;
    }

    public void setYouth(Integer youth) {
        this.youth = youth;
    }

    public Integer getRoomView() {
        return roomView;
    }

    public void setRoomView(Integer roomView) {
        this.roomView = roomView;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getMealBasisId() {
        return mealBasisId;
    }

    public void setMealBasisId(Integer mealBasisId) {
        this.mealBasisId = mealBasisId;
    }

    public Integer getChildAgeTo() {
        return childAgeTo;
    }

    public void setChildAgeTo(Integer childAgeTo) {
        this.childAgeTo = childAgeTo;
    }

    public Integer getYouthAgeFrom() {
        return youthAgeFrom;
    }

    public void setYouthAgeFrom(Integer youthAgeFrom) {
        this.youthAgeFrom = youthAgeFrom;
    }

    public Integer getYouthAgeTo() {
        return youthAgeTo;
    }

    public void setYouthAgeTo(Integer youthAgeTo) {
        this.youthAgeTo = youthAgeTo;
    }

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }
}

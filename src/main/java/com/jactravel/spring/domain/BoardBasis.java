package com.jactravel.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("board_basis")
public class BoardBasis {
    @PrimaryKey
    private Integer mealBasisId;
    private String mealBasisCode;
    @JsonIgnore
    @Transient
    private boolean isSync;

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public Integer getMealBasisId() {
        return mealBasisId;
    }

    public void setMealBasisId(Integer mealBasisId) {
        this.mealBasisId = mealBasisId;
    }

    public String getMealBasisCode() {
        return mealBasisCode;
    }

    public void setMealBasisCode(String mealBasisCode) {
        this.mealBasisCode = mealBasisCode;
    }
}

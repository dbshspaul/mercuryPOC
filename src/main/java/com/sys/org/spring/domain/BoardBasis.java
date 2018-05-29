package com.sys.org.spring.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("board_basis")
public class BoardBasis {
    @PrimaryKey
    private Integer mealBasisId;
    private String mealBasisCode;

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

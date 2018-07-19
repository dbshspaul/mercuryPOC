package com.jactravel.spring.domain;

import com.datastax.driver.core.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jactravel.spring.domain.idClass.ContractPK;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("contract")
public class Contract {
    @PrimaryKey
    private ContractPK contractPK;
    private LocalDate stayStartSate;
    private LocalDate stayEndDate;
    private LocalDate bookingStartSate;
    private LocalDate bookingEndSate;
    private Integer currencyId;
    private String currencyType;
    private String contractType;
    private Integer supplierId;
    private String contractBasis;
    private Boolean commissionOverride;
    private Boolean isCompleteStayOnly;
    private Integer bookingSourceId;
    private String suppliedBy;
    private String sourcePricePriority;
    private String priceType;

    @JsonIgnore
    @Transient
    private boolean isSync;

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public ContractPK getContractPK() {
        return contractPK;
    }

    public void setContractPK(ContractPK contractPK) {
        this.contractPK = contractPK;
    }

    public LocalDate getStayStartSate() {
        return stayStartSate;
    }

    public void setStayStartSate(LocalDate stayStartSate) {
        this.stayStartSate = stayStartSate;
    }

    public LocalDate getStayEndDate() {
        return stayEndDate;
    }

    public void setStayEndDate(LocalDate stayEndDate) {
        this.stayEndDate = stayEndDate;
    }

    public LocalDate getBookingStartSate() {
        return bookingStartSate;
    }

    public void setBookingStartSate(LocalDate bookingStartSate) {
        this.bookingStartSate = bookingStartSate;
    }

    public LocalDate getBookingEndSate() {
        return bookingEndSate;
    }

    public void setBookingEndSate(LocalDate bookingEndSate) {
        this.bookingEndSate = bookingEndSate;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getContractBasis() {
        return contractBasis;
    }

    public void setContractBasis(String contractBasis) {
        this.contractBasis = contractBasis;
    }

    public Boolean getCommissionOverride() {
        return commissionOverride;
    }

    public void setCommissionOverride(Boolean commissionOverride) {
        this.commissionOverride = commissionOverride;
    }

    public Boolean getCompleteStayOnly() {
        return isCompleteStayOnly;
    }

    public void setCompleteStayOnly(Boolean completeStayOnly) {
        isCompleteStayOnly = completeStayOnly;
    }

    public Integer getBookingSourceId() {
        return bookingSourceId;
    }

    public void setBookingSourceId(Integer bookingSourceId) {
        this.bookingSourceId = bookingSourceId;
    }

    public String getSuppliedBy() {
        return suppliedBy;
    }

    public void setSuppliedBy(String suppliedBy) {
        this.suppliedBy = suppliedBy;
    }

    public String getSourcePricePriority() {
        return sourcePricePriority;
    }

    public void setSourcePricePriority(String sourcePricePriority) {
        this.sourcePricePriority = sourcePricePriority;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }
}

package com.jactravel.spring.domain.idClass;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@PrimaryKeyClass
public class ContractPK {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private Integer contract_id;
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private Integer property_id;

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public Integer getProperty_id() {
        return property_id;
    }

    public void setProperty_id(Integer property_id) {
        this.property_id = property_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractPK that = (ContractPK) o;

        if (contract_id != null ? !contract_id.equals(that.contract_id) : that.contract_id != null) return false;
        return property_id != null ? property_id.equals(that.property_id) : that.property_id == null;
    }

    @Override
    public int hashCode() {
        int result = contract_id != null ? contract_id.hashCode() : 0;
        result = 31 * result + (property_id != null ? property_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContractPK{" +
                "contract_id=" + contract_id +
                ", property_id=" + property_id +
                '}';
    }
}

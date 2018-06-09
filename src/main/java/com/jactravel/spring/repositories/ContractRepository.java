package com.jactravel.spring.repositories;

import com.jactravel.spring.domain.Contract;
import com.jactravel.spring.domain.idClass.ContractPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends CrudRepository<Contract, ContractPK> {
}

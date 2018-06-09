package com.jactravel.spring.repositories;

import com.jactravel.spring.domain.BoardBasis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardBasisRepository extends CrudRepository<BoardBasis, Integer> {
}

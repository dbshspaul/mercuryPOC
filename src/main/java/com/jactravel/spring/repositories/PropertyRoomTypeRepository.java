package com.jactravel.spring.repositories;

import com.jactravel.spring.domain.PropertyRoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRoomTypeRepository extends CrudRepository<PropertyRoomType, Integer> {
}

package com.hoang.travel.repository;

import com.hoang.travel.entity.TouristPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristPointRepository extends CrudRepository<TouristPoint, Integer> {

}

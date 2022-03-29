package com.hoang.travel.repository;

import com.hoang.travel.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Integer>, PagingAndSortingRepository<CityEntity, Integer> {
    Page<CityEntity> findAllByCityNameContaining(String cityName, Pageable pageable);

//    @Query(value = "Select * from cities where id=:id", nativeQuery = true)
//    CityEntity findResort(@Param("id") int id);

}

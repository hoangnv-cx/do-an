package com.hoang.travel.repository;



import com.hoang.travel.entity.TourEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITourReponsitory extends CrudRepository<TourEntity, Integer>, PagingAndSortingRepository<TourEntity, Integer> {
    Page<TourEntity> findAllByTitleContaining(String city, Pageable pageable);
    @Query(value = "select * from tours where region_id=:id", nativeQuery = true)
    List<TourEntity> findTourEntityByRegionTour(@Param("id") int id);
}

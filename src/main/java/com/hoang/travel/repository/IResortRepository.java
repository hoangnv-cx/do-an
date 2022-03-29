package com.hoang.travel.repository;


import com.hoang.travel.entity.ResortEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResortRepository extends CrudRepository<ResortEntity, Integer>, PagingAndSortingRepository<ResortEntity, Integer> {
    Page<ResortEntity> findAllByTitleContaining(String city, Pageable pageable);

    @Query(value = "SELECT * FROM resorts WHERE id_city=:id" , nativeQuery = true)
    List<ResortEntity> listResortByCity(@Param("id") int  id);
}

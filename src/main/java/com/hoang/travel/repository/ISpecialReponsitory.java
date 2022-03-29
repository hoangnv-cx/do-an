package com.hoang.travel.repository;

import com.hoang.travel.entity.SpecialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISpecialReponsitory extends CrudRepository<SpecialEntity, Integer>, PagingAndSortingRepository<SpecialEntity, Integer> {
    Page<SpecialEntity> findAllByTitleContaining(String city, Pageable pageable);

    @Query(value = "SELECT * FROM specials WHERE city_id=:id" , nativeQuery = true)
    List<SpecialEntity> listSpecialByCity(@Param("id") int  id);
}

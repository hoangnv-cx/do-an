package com.hoang.travel.repository;


import com.hoang.travel.entity.CommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
    @Query(value = "Select * from comments where id_tour=:id", nativeQuery = true)
    List<CommentEntity> findByIdTour(@Param("id")int id);

    @Query(value = "Select * from comments where id_special=:id", nativeQuery = true)
    List<CommentEntity> findByIdSpecial(@Param("id")int id);

    @Query(value = "Select * from comments where id_resort=:id", nativeQuery = true)
    List<CommentEntity> findByIdResort(@Param("id")int id);
}

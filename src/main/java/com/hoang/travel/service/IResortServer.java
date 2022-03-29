package com.hoang.travel.service;

import com.hoang.travel.entity.ResortEntity;
import com.hoang.travel.model.ResortModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IResortServer {
    List<ResortModel> allResort();
    ResortModel addAndUpdateResort(ResortModel dto);
    ResortModel findResortByID(int id);
    void deleteResort(int id);
    Page<ResortEntity> findAllByCityContaining(String city, Pageable pageable);
    Page<ResortEntity> findAll(Pageable pageable);
    List<ResortEntity> listResortByCity(int id);
}

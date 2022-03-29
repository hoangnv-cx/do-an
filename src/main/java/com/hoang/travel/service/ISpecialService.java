package com.hoang.travel.service;

import com.hoang.travel.entity.SpecialEntity;
import com.hoang.travel.model.SpecialModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISpecialService {
    List<SpecialModel> allSpecial();
    SpecialModel addAndUpdateSpecial(SpecialModel dto);
    SpecialModel findSpecialByID(int id);
    void deleteSpecial(int id);
    Page<SpecialEntity> findAllByCityContaining(String city, Pageable pageable);
    Page<SpecialEntity> findAll(Pageable pageable);
    List<SpecialEntity> listSpecialByCity(int id);
}

package com.hoang.travel.service;


import com.hoang.travel.entity.CityEntity;
import com.hoang.travel.entity.SpecialEntity;
import com.hoang.travel.model.CityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICityService {
    List<CityModel> allCity();
    CityModel addAndUpdateCity(CityModel dto);
    CityModel findCityByID(int id);
    void deleteCity(int id);
    CityEntity findResort(int id);
    Page<CityEntity> findAllByCityNameContaining(String cityname, Pageable pageable);
    Page<CityEntity> findAll(Pageable pageable);
    List<SpecialEntity> listSpecial();
}

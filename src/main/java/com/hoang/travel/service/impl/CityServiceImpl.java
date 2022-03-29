package com.hoang.travel.service.impl;

import com.hoang.travel.convert.CityConvert;
import com.hoang.travel.entity.CityEntity;
import com.hoang.travel.entity.SpecialEntity;
import com.hoang.travel.model.CityModel;
import com.hoang.travel.repository.CityRepository;
import com.hoang.travel.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityConvert cityConvert;


    @Override
    public List<CityModel> allCity() {
        List<CityEntity> cityEntities = (List<CityEntity>) cityRepository.findAll();
        List<CityModel> cityModels =new ArrayList<>();
        for(CityEntity cityEntity : cityEntities){
            CityModel cityModel = cityConvert.entityToDto(cityEntity);
            cityModel.setRegion(cityEntity.getRegion());
            cityModels.add(cityModel);
        }
        return cityModels;
    }

    @Override
    public CityModel addAndUpdateCity(CityModel dto) {
        if(dto.getId() == null){
            CityEntity cityEntity = cityConvert.dtoToEntity(dto);
            cityEntity.setRegion(dto.getRegion());
            cityRepository.save(cityEntity);
        }else{
            CityEntity cityEntity = cityConvert.dtoToEntity(dto , cityRepository.findById(dto.getId()).orElse(null));
            cityEntity.setRegion(dto.getRegion());
            cityRepository.save(cityEntity);
        }
        return dto;
    }

    @Override
    public CityModel findCityByID(int id) {
        CityEntity cityEntity=cityRepository.findById(id).get();
        CityModel cityModel=cityConvert.entityToDto(cityEntity);
        cityModel.setRegion(cityEntity.getRegion());
        return cityModel;
    }

    @Override
    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public CityEntity findResort(int id) {
        return cityRepository.findById(id).get();
    }



    @Override
    public Page<CityEntity> findAllByCityNameContaining(String cityname, Pageable pageable) {
        return cityRepository.findAllByCityNameContaining(cityname,pageable);
    }

    @Override
    public Page<CityEntity> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public List<SpecialEntity> listSpecial() {
        return null;
    }


}

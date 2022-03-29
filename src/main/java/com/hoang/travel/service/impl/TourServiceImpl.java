package com.hoang.travel.service.impl;

import com.hoang.travel.convert.TourConvert;
import com.hoang.travel.entity.TourEntity;
import com.hoang.travel.model.TourModel;
import com.hoang.travel.repository.ITourReponsitory;
import com.hoang.travel.service.ICityService;
import com.hoang.travel.service.IRegionService;
import com.hoang.travel.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourServiceImpl implements ITourService {
    @Autowired
    private ITourReponsitory tourReponsitory;
    @Autowired
    private ICityService cityService;
    @Autowired
    private IRegionService regionService;
    @Autowired
    TourConvert tourConvert;
    @Override
    public List<TourModel> allTour() {
        List<TourEntity> tourEntities= (List<TourEntity>) tourReponsitory.findAll();
        List<TourModel> tourModels=new ArrayList<>();
        for(TourEntity tourEntity:tourEntities){
            TourModel tourModel = tourConvert.entityToDto(tourEntity);
            tourModel.setCityEnd(tourEntity.getCityEnd());
            tourModel.setCityStart(tourEntity.getCityStart());
            tourModel.setRegionTour(tourEntity.getRegionTour());
            tourModels.add(tourModel);
        }
        return tourModels;
    }

    @Override
    public TourModel addAndUpdateTour(TourModel dto) {
        if(dto.getId() == null){
            TourEntity tourEntity=tourConvert.dtoToEntity(dto);
            tourEntity.setCityStart(dto.getCityStart());
            tourEntity.setCityEnd(dto.getCityEnd());
            tourEntity.setRegionTour(dto.getRegionTour());
            tourReponsitory.save(tourEntity);
        }else{
            TourEntity tourEntity=tourConvert.dtoToEntity(dto,tourReponsitory.findById(dto.getId()).orElse(null));
            tourEntity.setCityStart(dto.getCityStart());
            tourEntity.setCityEnd(dto.getCityEnd());
            tourEntity.setRegionTour(dto.getRegionTour());
            tourReponsitory.save(tourEntity);
        }
        return dto;
    }

    @Override
    public TourModel findTourByID(int id) {
        TourEntity tourEntity = tourReponsitory.findById(id).orElse(null);
        TourModel tourModel = tourConvert.entityToDto(tourEntity);
        tourModel.setCityEnd(tourEntity.getCityEnd());
        tourModel.setCityStart(tourEntity.getCityStart());
        tourModel.setRegionTour(tourEntity.getRegionTour());
        return tourModel;
    }

    @Override
    public void deleteTour(int id) {
        tourReponsitory.deleteById(id);
    }

    @Override
    public void processPage(String URL) {

    }

    @Override
    public Page<TourEntity> findAllByTitleContaining(String city, Pageable pageable) {
        return tourReponsitory.findAllByTitleContaining(city, pageable);
    }

    @Override
    public Page<TourEntity> findAll(Pageable pageable) {
        return tourReponsitory.findAll(pageable);
    }

    @Override
    public List<TourEntity> findTourEntityByRegionTour(int id) {
        return tourReponsitory.findTourEntityByRegionTour(id);
    }
}

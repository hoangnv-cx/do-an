package com.hoang.travel.service;

import com.hoang.travel.entity.RegionEntity;
import com.hoang.travel.model.RegionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRegionService {
    List<RegionModel> allRegion();
    RegionModel addAndUpdateRegion(RegionModel dto);
    RegionModel findRegionByID(int id);
    void deleteRegion(int id);
//    RegionEntity findResort(int id);
    Page<RegionEntity> findAllByRegionNameContaining(String regionName, Pageable pageable);
    Page<RegionEntity> findAll(Pageable pageable);
}

package com.hoang.travel.service.impl;

import com.hoang.travel.convert.RegionConvert;
import com.hoang.travel.entity.RegionEntity;
import com.hoang.travel.model.RegionModel;
import com.hoang.travel.repository.IRegionRepository;
import com.hoang.travel.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private IRegionRepository regionRepository;
    @Autowired
    private RegionConvert regionConvert;
    @Override
    public List<RegionModel> allRegion() {
        List<RegionEntity> regionEntities = (List<RegionEntity>) regionRepository.findAll();
        List<RegionModel> regionModels = new ArrayList<>();
        for(RegionEntity entity : regionEntities){
            RegionModel regionModel=new RegionModel();
            regionModel = regionConvert.entityToDto(entity);
            regionModels.add(regionModel);
        }
        return regionModels;
    }

    @Override
    public RegionModel addAndUpdateRegion(RegionModel dto) {
        if(dto.getId() == null){
            regionRepository.save(regionConvert.dtoToEntity(dto));
        }else {
            regionRepository.save(regionConvert.dtoToEntity(dto , regionRepository.findById(dto.getId()).orElse(null)));
        }
        return dto;
    }

    @Override
    public RegionModel findRegionByID(int id) {
        return regionConvert.entityToDto(regionRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteRegion(int id) {
        regionRepository.deleteById(id);
    }

    @Override
    public Page<RegionEntity> findAllByRegionNameContaining(String title, Pageable pageable) {
        return regionRepository.findAllByTitleContaining(title,pageable);
    }

    @Override
    public Page<RegionEntity> findAll(Pageable pageable) {
        return regionRepository.findAll(pageable);
    }
}

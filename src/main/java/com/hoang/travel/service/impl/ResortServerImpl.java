package com.hoang.travel.service.impl;

import com.hoang.travel.convert.ResortConvert;
import com.hoang.travel.entity.ResortEntity;
import com.hoang.travel.model.ResortModel;
import com.hoang.travel.repository.IResortRepository;
import com.hoang.travel.service.IResortServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResortServerImpl implements IResortServer {
    @Autowired
    private IResortRepository resortRepository;
    @Autowired
    private ResortConvert resortConvert;
    @Override
    public List<ResortModel> allResort() {
        List<ResortEntity> resortEntities=(List<ResortEntity>) resortRepository.findAll();
        List<ResortModel> resortModels=new ArrayList<>();
        for(ResortEntity resortEntity: resortEntities){
            ResortModel resortModel = resortConvert.entityToDto(resortEntity);
            resortModel.setResortCity(resortEntity.getResortCity());
            resortModels.add(resortModel);
        }
        return resortModels;
    }

    @Override
    public ResortModel addAndUpdateResort(ResortModel dto) {
        if(dto.getId() == null){
            ResortEntity resortEntity = resortConvert.dtoToEntity(dto);
            resortEntity.setResortCity(dto.getResortCity());
            resortRepository.save(resortEntity);
        }else{
            ResortEntity resortEntity = resortConvert.dtoToEntity(dto,resortRepository.findById(dto.getId()).orElse(null));
            resortEntity.setResortCity(dto.getResortCity());
            resortRepository.save(resortEntity);
        }
        return dto;
    }

    @Override
    public ResortModel findResortByID(int id) {
        ResortEntity resortEntity=resortRepository.findById(id).orElse(null);
        ResortModel resortModel = resortConvert.entityToDto(resortEntity);
        resortModel.setResortCity(resortEntity.getResortCity());
        return resortModel;
    }

    @Override
    public void deleteResort(int id) {
        resortRepository.deleteById(id);
    }

    @Override
    public Page<ResortEntity> findAllByCityContaining(String city, Pageable pageable) {
        return resortRepository.findAllByTitleContaining(city, pageable);
    }

    @Override
    public Page<ResortEntity> findAll(Pageable pageable) {
        return resortRepository.findAll(pageable);
    }

    @Override
    public List<ResortEntity> listResortByCity(int id) {
        return resortRepository.listResortByCity(id);
    }
}

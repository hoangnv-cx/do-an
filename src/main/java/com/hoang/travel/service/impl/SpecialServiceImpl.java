package com.hoang.travel.service.impl;

import com.hoang.travel.convert.SpecialConvert;
import com.hoang.travel.entity.SpecialEntity;
import com.hoang.travel.model.SpecialModel;
import com.hoang.travel.repository.ISpecialReponsitory;
import com.hoang.travel.service.ISpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialServiceImpl implements ISpecialService {
    @Autowired
    private ISpecialReponsitory specialReponsitory;
    @Autowired
    private SpecialConvert specialConvert;
    @Override
    public List<SpecialModel> allSpecial() {
        List<SpecialEntity> specialEntities = (List<SpecialEntity>) specialReponsitory.findAll();
        List<SpecialModel> specialModels=new ArrayList<>();
        for(SpecialEntity specialEntity : specialEntities){
            SpecialModel specialModel= specialConvert.entityToDto(specialEntity);
            specialModel.setSpecialCity(specialEntity.getSpecialCity());
            specialModels.add(specialModel);
        }
        return specialModels;
    }

    @Override
    public SpecialModel addAndUpdateSpecial(SpecialModel dto) {
        if(dto.getId() == null){
            SpecialEntity specialEntity=specialConvert.dtoToEntity(dto);
            specialEntity.setSpecialCity(dto.getSpecialCity());
            specialReponsitory.save(specialEntity);
        }else{
            SpecialEntity specialEntity=specialReponsitory.findById(dto.getId()).orElse(null);
            specialEntity.setSpecialCity(dto.getSpecialCity());
            specialReponsitory.save(specialConvert.dtoToEntity(dto , specialEntity));
        }
        return dto;
    }

    @Override
    public SpecialModel findSpecialByID(int id) {
        SpecialEntity specialEntity=specialReponsitory.findById(id).orElse(null);
        SpecialModel specialModel=specialConvert.entityToDto(specialEntity);
        specialModel.setSpecialCity(specialEntity.getSpecialCity());
        return specialModel;
    }

    @Override
    public void deleteSpecial(int id) {
        specialReponsitory.deleteById(id);
    }

    @Override
    public Page<SpecialEntity> findAllByCityContaining(String city, Pageable pageable) {
        return specialReponsitory.findAllByTitleContaining(city,pageable);
    }

    @Override
    public Page<SpecialEntity> findAll(Pageable pageable) {
        return specialReponsitory.findAll(pageable);
    }

    @Override
    public List<SpecialEntity> listSpecialByCity(int id) {
        return specialReponsitory.listSpecialByCity(id);
    }
}

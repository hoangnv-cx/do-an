package com.hoang.travel.convert;

import com.hoang.travel.entity.SpecialEntity;
import com.hoang.travel.model.SpecialModel;
import org.springframework.stereotype.Component;

@Component
public class SpecialConvert {
    public SpecialEntity dtoToEntity(SpecialModel dto) {
        SpecialEntity entity=new SpecialEntity();
        entity.setTitle(dto.getTitle());
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setCost(dto.getCost());
        return entity;
    }
    public SpecialModel entityToDto(SpecialEntity entity) {
        SpecialModel dto =new SpecialModel();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setLocation(entity.getLocation());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        dto.setCost(entity.getCost());
        return dto;
    }
    public SpecialEntity dtoToEntity(SpecialModel dto,SpecialEntity entity) {
        entity.setTitle(dto.getTitle());
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        entity.setCost(dto.getCost());
        if(!dto.getFileImg().getOriginalFilename().equals("") ){
            entity.setImage(dto.getImage());
        }
        return entity;
    }
}

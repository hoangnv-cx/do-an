package com.hoang.travel.convert;

import com.hoang.travel.entity.RegionEntity;
import com.hoang.travel.model.RegionModel;
import org.springframework.stereotype.Component;

@Component
public class RegionConvert {
    public RegionEntity dtoToEntity(RegionModel dto) {
        RegionEntity entity=new RegionEntity();
        entity.setRegionName(dto.getRegionName());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setSortDescription(dto.getSortDescription());
        entity.setDescription(dto.getDescription());
        return entity;
    }
    public RegionModel entityToDto(RegionEntity entity) {
        RegionModel dto =new RegionModel();
        dto.setId(entity.getId());
        dto.setRegionName(entity.getRegionName());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setSortDescription(entity.getSortDescription());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    public RegionEntity dtoToEntity(RegionModel dto,RegionEntity entity) {
        entity.setRegionName(dto.getRegionName());

        entity.setTitle(dto.getTitle());
        entity.setSortDescription(dto.getSortDescription());
        entity.setDescription(dto.getDescription());
        if(!dto.getFileImg().getOriginalFilename().equals("") ){
            entity.setImage(dto.getImage());
        }
        return entity;
    }
}

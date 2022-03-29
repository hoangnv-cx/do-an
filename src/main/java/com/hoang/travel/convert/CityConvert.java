package com.hoang.travel.convert;

import com.hoang.travel.entity.CityEntity;
import com.hoang.travel.model.CityModel;
import org.springframework.stereotype.Component;

@Component
public class CityConvert {
    public CityEntity dtoToEntity(CityModel dto) {
        CityEntity entity=new CityEntity();
        entity.setCityName(dto.getCityName());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setTitleProduct(dto.getTitleProduct());
        return entity;
    }
    public CityModel entityToDto(CityEntity entity) {
        CityModel dto =new CityModel();
        dto.setId(entity.getId());
        dto.setCityName(entity.getCityName());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setTitleProduct(entity.getTitleProduct());
        return dto;
    }
    public CityEntity dtoToEntity(CityModel dto,CityEntity entity) {

        return entity;
    }
}

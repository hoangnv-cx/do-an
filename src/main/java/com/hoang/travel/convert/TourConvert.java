package com.hoang.travel.convert;

import com.hoang.travel.entity.TourEntity;
import com.hoang.travel.model.TourModel;
import org.springframework.stereotype.Component;

@Component
public class TourConvert {
    public TourEntity dtoToEntity(TourModel dto) {
       TourEntity entity=new TourEntity();
        entity.setName(dto.getName());
        entity.setTitle(dto.getTitle());
        entity.setSchedule(dto.getSchedule());
        entity.setVehicle(dto.getVehicle());
        entity.setDescription(dto.getDescription());
        entity.setCost(dto.getCost());

        return entity;
    }
    public TourModel entityToDto(TourEntity entity) {
       TourModel dto=new TourModel();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage());
        dto.setSchedule(entity.getSchedule());
        dto.setVehicle(entity.getVehicle());
        dto.setDescription(entity.getDescription());
        dto.setCost(entity.getCost());
        return dto;
    }
    public TourEntity dtoToEntity(TourModel dto,TourEntity entity) {
        entity.setName(dto.getName());
        entity.setTitle(dto.getTitle());
        entity.setSchedule(dto.getSchedule());
        entity.setVehicle(dto.getVehicle());
        entity.setDescription(dto.getDescription());
        entity.setCost(dto.getCost());
        if(!dto.getFileImg().getOriginalFilename().equals("") ){
            entity.setImage(dto.getImage());
        }
        return entity;
    }
}

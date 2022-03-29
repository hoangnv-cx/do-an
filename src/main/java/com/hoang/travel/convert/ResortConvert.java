package com.hoang.travel.convert;

import com.hoang.travel.entity.CityEntity;
import com.hoang.travel.entity.CommentEntity;
import com.hoang.travel.entity.ResortEntity;
import com.hoang.travel.entity.SpecialEntity;
import com.hoang.travel.model.ResortModel;
import com.hoang.travel.model.SpecialModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ResortConvert {

    public ResortEntity dtoToEntity(ResortModel dto) {
        ResortEntity entity=new ResortEntity();
        entity.setTitle(dto.getTitle());
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setVehicle(dto.getVehicle());
        entity.setCost(dto.getCost());
        entity.setConvenient(dto.getConvenient());
        return entity;
    }
    public ResortModel entityToDto(ResortEntity entity) {
        ResortModel dto =new ResortModel();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setLocation(entity.getLocation());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage());
        dto.setVehicle(entity.getVehicle());
        dto.setCost(entity.getCost());
        dto.setConvenient(entity.getConvenient());
        return dto;
    }
    public ResortEntity dtoToEntity(ResortModel dto,ResortEntity entity) {
        entity.setTitle(dto.getTitle());
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setVehicle(dto.getVehicle());
        entity.setCost(dto.getCost());
        entity.setConvenient(dto.getConvenient());
        if(!dto.getFileImg().getOriginalFilename().equals("") ){
            entity.setImage(dto.getImage());
        }
        return entity;
    }
}

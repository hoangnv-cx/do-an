package com.hoang.travel.convert;

import com.hoang.travel.entity.*;
import com.hoang.travel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CommentConvert {

    @Autowired
    private ResortConvert resortConvert;
    @Autowired
    private TourConvert tourConvert;
    @Autowired
    private SpecialConvert specialConvert;

    public CommentEntity dtoToEntity(CommentModel dto) {
        CommentEntity entity=new CommentEntity();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUserComment(dto.getUserComment());
        if(dto.getIdResort() != null){
            ResortEntity entityt = resortConvert.dtoToEntity(dto.getIdResort());
            entityt.setId(dto.getIdResort().getId());
            entity.setIdResort(entityt);
        }
        if(dto.getIdSpecial() != null){
            SpecialEntity entityt = specialConvert.dtoToEntity(dto.getIdSpecial());
            entityt.setId(dto.getIdSpecial().getId());
            entity.setIdSpecial(entityt);
        }
        if(dto.getIdTour() != null){
            TourEntity entityt = tourConvert.dtoToEntity(dto.getIdTour());
            entityt.setId(dto.getIdTour().getId());
            entity.setIdTour(entityt);
        }

        return entity;
    }
    public CommentModel entityToDto(CommentEntity entity) {
        CommentModel dto =new CommentModel();
        dto.setId(entity.getId());
        return dto;
    }
    public CommentEntity dtoToEntity(CommentModel dto,CommentEntity entity) {
        return entity;
    }
}

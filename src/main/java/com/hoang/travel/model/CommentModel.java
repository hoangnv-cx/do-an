package com.hoang.travel.model;

import com.hoang.travel.entity.ResortEntity;
import com.hoang.travel.entity.SpecialEntity;
import com.hoang.travel.entity.TourEntity;
import com.hoang.travel.entity.UserEntity;

import javax.persistence.*;
import java.sql.Timestamp;

public class CommentModel {

    private int id;
    private String content;
    private Timestamp createdAt;
    private UserEntity userComment;
    private TourModel idTour;
    private ResortModel idResort;
    private SpecialModel idSpecial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUserComment() {
        return userComment;
    }

    public void setUserComment(UserEntity userComment) {
        this.userComment = userComment;
    }

    public TourModel getIdTour() {
        return idTour;
    }

    public void setIdTour(TourModel idTour) {
        this.idTour = idTour;
    }

    public ResortModel getIdResort() {
        return idResort;
    }

    public void setIdResort(ResortModel idResort) {
        this.idResort = idResort;
    }

    public SpecialModel getIdSpecial() {
        return idSpecial;
    }

    public void setIdSpecial(SpecialModel idSpecial) {
        this.idSpecial = idSpecial;
    }
}



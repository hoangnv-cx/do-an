package com.hoang.travel.model;

import com.hoang.travel.entity.CityEntity;
import com.hoang.travel.entity.CommentEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class SpecialModel {

    private Integer id;
    private String title;
    private String location;
    private String description;
    private String image;
    private MultipartFile fileImg;
    private CityEntity specialCity;
    private List<CommentEntity> comment = new ArrayList<>();
    private String cost;

    public MultipartFile getFileImg() {
        return fileImg;
    }

    public void setFileImg(MultipartFile fileImg) {
        this.fileImg = fileImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CityEntity getSpecialCity() {
        return specialCity;
    }

    public void setSpecialCity(CityEntity specialCity) {
        this.specialCity = specialCity;
    }

    public List<CommentEntity> getComment() {
        return comment;
    }

    public void setComment(List<CommentEntity> comment) {
        this.comment = comment;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}

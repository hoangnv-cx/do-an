package com.hoang.travel.model;

import com.hoang.travel.entity.CityEntity;
import com.hoang.travel.entity.CommentEntity;
import com.hoang.travel.entity.RegionEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class TourModel {

    private Integer id;
    private String title;
    private String name;
    private String description;
    private MultipartFile fileImg;
    private String image;
    private CityEntity cityStart;
    private CityEntity cityEnd;
    private List<CommentEntity> comment = new ArrayList<>();
    private RegionEntity regionTour;
    private String vehicle;
    private String schedule;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CityEntity getCityStart() {
        return cityStart;
    }

    public void setCityStart(CityEntity cityStart) {
        this.cityStart = cityStart;
    }

    public CityEntity getCityEnd() {
        return cityEnd;
    }

    public void setCityEnd(CityEntity cityEnd) {
        this.cityEnd = cityEnd;
    }

    public List<CommentEntity> getComment() {
        return comment;
    }

    public void setComment(List<CommentEntity> comment) {
        this.comment = comment;
    }

    public RegionEntity getRegionTour() {
        return regionTour;
    }

    public void setRegionTour(RegionEntity regionTour) {
        this.regionTour = regionTour;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}

package com.hoang.travel.model;


import com.hoang.travel.entity.RegionEntity;
import org.springframework.web.multipart.MultipartFile;

public class CityModel {
    private Integer id;
    private String cityName;
    private String image;
    private String description;
    private String title;
    private String titleProduct;
    private RegionEntity region;
    private MultipartFile fileImg;

    public MultipartFile getFileImg() {
        return fileImg;
    }

    public void setFileImg(MultipartFile fileImg) {
        this.fileImg = fileImg;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }
}

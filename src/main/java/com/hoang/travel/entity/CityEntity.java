package com.hoang.travel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities", schema = "tourism_products")
public class CityEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;
    @Column(name = "cities_name")
    private String cityName;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "title")
    private String title;
    @Column(name = "titleProduct")
    private String titleProduct;

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public void setSpecial(List<SpecialEntity> special) {
        this.special = special;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "resortCity")
    private List<ResortEntity> resorts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cityStart", fetch = FetchType.LAZY)
    private List<TourEntity> cityStart = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cityEnd", fetch = FetchType.LAZY)
    private List<TourEntity> cityEnd = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cityTouristPoint")
    private List<TouristPoint> tourisPoint = new ArrayList<>();

    @OneToMany(mappedBy = "specialCity")
    private List<SpecialEntity> special  = new ArrayList<>();

    public List<TourEntity> getCityStart() {
        return cityStart;
    }

    public List<SpecialEntity> getSpecial() {
        return special;
    }

    public void setCityStart(List<TourEntity> cityStart) {
        this.cityStart = cityStart;
    }

    public List<TourEntity> getCityEnd() {
        return cityEnd;
    }

    public void setCityEnd(List<TourEntity> cityEnd) {
        this.cityEnd = cityEnd;
    }

    public List<TouristPoint> getTourisPoint() {
        return tourisPoint;
    }

    public void setTourisPoint(List<TouristPoint> tourisPoint) {
        this.tourisPoint = tourisPoint;
    }

    public List<ResortEntity> getResorts() {
        return resorts;
    }

    public void setResorts(List<ResortEntity> resorts) {
        this.resorts = resorts;
    }

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private RegionEntity region;

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

}

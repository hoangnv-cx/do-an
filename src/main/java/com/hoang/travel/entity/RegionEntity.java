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
@Table(name = "regions", schema = "tourism_products")
public class RegionEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "image")
    private String image;
    @Column(name = "title")
    private String title;
    @Column(name = "sort_description")
    private String sortDescription;
    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TourEntity> getRegionTour() {
        return regionTour;
    }

    public void setRegionTour(List<TourEntity> regionTour) {
        this.regionTour = regionTour;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private List<CityEntity> city  = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "regionTour")
    private List<TourEntity> regionTour = new ArrayList<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }



    public List<CityEntity> getCity() {
        return city;
    }

    public void setCity(List<CityEntity> city) {
        this.city = city;
    }
}

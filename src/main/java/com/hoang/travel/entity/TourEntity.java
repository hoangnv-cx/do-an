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
@Table(name = "tours", schema = "tourism_products")
public class TourEntity {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ManyToOne
    @JoinColumn(name = "cityStartId", referencedColumnName = "id")
    private CityEntity cityStart;

    @ManyToOne
    @JoinColumn(name = "cityEndtId", referencedColumnName = "id")
    private CityEntity cityEnd;

    @JsonIgnore
    @OneToMany(mappedBy = "idTour")
    private List<CommentEntity> comment = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private RegionEntity regionTour;

    @Column(name = "vehicle")
    private String vehicle;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "cost")
    private String cost;

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

    public RegionEntity getRegionTour() {
        return regionTour;
    }

    public void setRegionTour(RegionEntity regionTour) {
        this.regionTour = regionTour;
    }

    public List<CommentEntity> getComment() {
        return comment;
    }

    public void setComment(List<CommentEntity> comment) {
        this.comment = comment;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}

package com.hoang.travel.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "touristPoint", schema = "tourism_products")
public class TouristPoint {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "distance")
    private String distance;

    @Column(name = "vehicle")
    private String vehicle;

    @Column(name = "schedule")
    private String schedule;

    @ManyToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id")
    private ResortEntity cityTouristPoint;



}

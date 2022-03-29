package com.hoang.travel.service;


import com.hoang.travel.entity.TouristPoint;

import java.util.List;

public interface ITouristPointService {
    TouristPoint findByID(int id);

    List<TouristPoint> findAllNature();
}

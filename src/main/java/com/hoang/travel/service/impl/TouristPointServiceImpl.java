package com.hoang.travel.service.impl;

import com.hoang.travel.entity.TouristPoint;
import com.hoang.travel.repository.TouristPointRepository;
import com.hoang.travel.service.ITouristPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristPointServiceImpl implements ITouristPointService {
    @Autowired
    private TouristPointRepository touristPointRepository;
    @Override
    public TouristPoint findByID(int id) {
        return touristPointRepository.findById(id).get();
    }

    @Override
    public List<TouristPoint> findAllNature() {

        return (List<TouristPoint>) touristPointRepository.findAll();
    }

}

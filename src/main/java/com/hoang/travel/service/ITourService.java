package com.hoang.travel.service;


import com.hoang.travel.entity.TourEntity;
import com.hoang.travel.model.TourModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITourService {
    List<TourModel> allTour();
    TourModel addAndUpdateTour(TourModel dto);
    TourModel findTourByID(int id);
    void deleteTour(int id);
    //lấy dữ liệu từ trang web
    void processPage(String URL);
    Page<TourEntity> findAllByTitleContaining(String city, Pageable pageable);
    Page<TourEntity> findAll(Pageable pageable);
    List<TourEntity> findTourEntityByRegionTour(int id);
}

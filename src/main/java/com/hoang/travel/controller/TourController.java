package com.hoang.travel.controller;

import com.hoang.travel.entity.TourEntity;
import com.hoang.travel.model.TourModel;
import com.hoang.travel.service.ICityService;
import com.hoang.travel.service.IRegionService;
import com.hoang.travel.service.ITourService;
import com.hoang.travel.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/management")
public class TourController {
    @Autowired
    private ITourService tourService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private IRegionService regionService;
    @Autowired
    private UploadFile uploadFile;

    @RequestMapping(value="/tours")
    public String allCity(Model model){
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("region", regionService.allRegion());
        model.addAttribute("listTour", tourService.allTour());
        return "management/tour/listTour";
    }

    @RequestMapping ("/tour/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        tourService.deleteTour(id);
        return "redirect:/management/tours";
    }

    @RequestMapping(value = "/tour/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("region", regionService.allRegion());
        model.addAttribute("tourID", tourService.findTourByID(id));
        return "management/tour/editTour";
    }
    @RequestMapping(value = "/tour/update/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("tourID") TourModel dto,
                             BindingResult result, Model model) {
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        if (result.hasErrors()) {
            dto.setId(id);
            return "management/cityList";
        }
        tourService.addAndUpdateTour(dto);
        return "redirect:/management/tours";
    }

    @RequestMapping(value = "/tour/add", method = RequestMethod.GET)
    public String AddUser(Model model) {
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("region", regionService.allRegion());
        model.addAttribute("tour", new TourModel());
        return "management/tour/addTour";
    }

    @RequestMapping(value = "/tour/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("tour") TourModel dto){
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        tourService.addAndUpdateTour(dto);
        return "redirect:/management/tours";
    }

}

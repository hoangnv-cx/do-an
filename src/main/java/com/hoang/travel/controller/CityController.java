package com.hoang.travel.controller;

import com.hoang.travel.model.CityModel;
import com.hoang.travel.service.ICityService;
import com.hoang.travel.service.IRegionService;
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
public class CityController {
    @Autowired
    private ICityService cityService;
    @Autowired
    private IRegionService regionService;
    @Autowired
    private UploadFile uploadFile;

    @RequestMapping(value="/cities")
    public String allCity(Model model){
        model.addAttribute("cityList", cityService.allCity());
        return "management/city/cityList";
    }

    @RequestMapping ("/city/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        cityService.deleteCity(id);
        return "redirect:/management/cities";
    }

    @RequestMapping(value = "/city/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("regionList", regionService.allRegion());
        model.addAttribute("cityId", cityService.findCityByID(id));
        return "management/city/editCity";
    }
    @RequestMapping(value = "/city/update/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("cityId") CityModel dto,
                             BindingResult result, Model model) {
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        if (result.hasErrors()) {
            dto.setId(id);
            return "management/city/editCity";
        }
        cityService.addAndUpdateCity(dto);
        return "redirect:/management/cities";
    }

    @RequestMapping(value = "/city/add", method = RequestMethod.GET)
    public String AddUser(Model model) {
        model.addAttribute("regionList", regionService.allRegion());
        model.addAttribute("cities", new CityModel());
        return "management/city/cityAdd";
    }

    @RequestMapping(value = "/city/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("cities") CityModel dto){
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        cityService.addAndUpdateCity(dto);
        return "redirect:/management/cities";
    }

}

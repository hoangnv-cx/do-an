package com.hoang.travel.controller;

import com.hoang.travel.model.RegionModel;
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
@RequestMapping(value="/management")
public class RegionController {
    @Autowired
    private IRegionService regionService;
    @Autowired
    private UploadFile uploadFile;

    @RequestMapping(value="/regions")
    public String allCity(Model model){
        model.addAttribute("listRegion", regionService.allRegion());
        return "management/region/regionList";
    }

    @RequestMapping ("/region/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        regionService.deleteRegion(id);
        return "redirect:/management/regions";
    }

    @RequestMapping(value = "/region/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("regionId", regionService.findRegionByID(id));
        return "management/region/regionEdit";
    }
    @RequestMapping(value = "/region/update/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("regionId") RegionModel dto,
                             BindingResult result, Model model) {
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        if (result.hasErrors()) {
            dto.setId(id);
            return "management/region/regionEdit";
        }
        regionService.addAndUpdateRegion(dto);
        return "redirect:/management/regions";
    }

    @RequestMapping(value = "/region/add", method = RequestMethod.GET)
    public String AddRegion(Model model) {
        model.addAttribute("regions", new RegionModel());
        return "management/region/regionAdd";
    }

    @RequestMapping(value = "/region/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("regions")RegionModel dto){
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        regionService.addAndUpdateRegion(dto);
        return "redirect:/management/regions";
    }
}

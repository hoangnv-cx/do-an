package com.hoang.travel.controller;

import com.hoang.travel.entity.ResortEntity;
import com.hoang.travel.model.ResortModel;
import com.hoang.travel.service.ICityService;
import com.hoang.travel.service.IResortServer;
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
public class ResortController {
    @Autowired
    private IResortServer resortServer;
    @Autowired
    private ICityService cityService;
    @Autowired
    private UploadFile uploadFile;

    @RequestMapping(value="/resorts")
    public String allCity(Model model){
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("listResort", resortServer.allResort());
        return "management/resort/listResort";
    }

    @RequestMapping ("/resort/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        resortServer.deleteResort(id);
        return "redirect:/management/resorts";
    }

    @RequestMapping(value = "/resort/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("resortID", resortServer.findResortByID(id));
        return "management/resort/editResort";
    }
    @RequestMapping(value = "/resort/update/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("resortID") ResortModel dto,
                             BindingResult result, Model model) {
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        if (result.hasErrors()) {
            dto.setId(id);
            return "management/special/listSpecial";
        }
        resortServer.addAndUpdateResort(dto);
        return "redirect:/management/resorts";
    }

    @RequestMapping(value = "/resort/add", method = RequestMethod.GET)
    public String AddUser(Model model) {
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("resort", new ResortModel());
        return "management/resort/addResort";
    }

    @RequestMapping(value = "/resort/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("resort") ResortModel dto){
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        resortServer.addAndUpdateResort(dto);
        return "redirect:/management/resorts";
    }
}

package com.hoang.travel.controller;


import com.hoang.travel.entity.SpecialEntity;
import com.hoang.travel.model.SpecialModel;
import com.hoang.travel.service.ICityService;
import com.hoang.travel.service.ISpecialService;
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
public class SpecialController {
    @Autowired
    private ISpecialService specialService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private UploadFile uploadFile;

    @RequestMapping(value="/specials")
    public String allCity(Model model){
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("listSpecial", specialService.allSpecial());
        return "management/special/listSpecial";
    }

    @RequestMapping ("/special/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        specialService.deleteSpecial(id);
        return "redirect:/management/specials";
    }

    @RequestMapping(value = "/special/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("specialID", specialService.findSpecialByID(id));
        return "management/special/editSpecial";
    }
    @RequestMapping(value = "/special/update/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("specialID") SpecialModel dto,
                             BindingResult result, Model model) {
        String a = dto.getFileImg().getOriginalFilename();
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        specialService.addAndUpdateSpecial(dto);
        return "redirect:/management/specials";
    }

    @RequestMapping(value = "/special/add", method = RequestMethod.GET)
    public String AddUser(Model model) {
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("special", new SpecialModel());
        return "management/special/addSpecial";
    }

    @RequestMapping(value = "/special/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("special") SpecialModel dto){
        dto.setImage(uploadFile.updateImage(dto.getFileImg()));
        specialService.addAndUpdateSpecial(dto);
        return "redirect:/management/specials";
    }
}

package com.hoang.travel.controller;

import com.hoang.travel.entity.CityEntity;
import com.hoang.travel.entity.CommentEntity;
import com.hoang.travel.entity.RegionEntity;
import com.hoang.travel.entity.TourEntity;
import com.hoang.travel.service.*;
import com.hoang.travel.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping(value="/")
public class UserController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ITourService tourService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private ISpecialService specialService;
    @Autowired
    private IResortServer resortServer;
    @Autowired
    private ICommentService commentService;

    @RequestMapping(value={"/","/index"})
    public String allProduct(Model model) throws IOException {
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("username", SecurityUtils.getName());
        return "userGUI/home";
    }

    @RequestMapping(value="/tour/{id}")
    public String CategoryTour(@PathVariable("id") int id, Model model) {
        model.addAttribute("username", SecurityUtils.getName());
        model.addAttribute("tourCategory", tourService.findTourByID(id));
        model.addAttribute("regions", regionService.allRegion());
        model.addAttribute("tourComment", commentService.getCommentByTour(id));
        model.addAttribute("comment",new CommentEntity());
        return "userGUI/tour";
    }
    @RequestMapping(value="/tour")
    public ModelAndView listTour(@RequestParam("tour") Optional<String> s, Pageable pageable, @RequestParam(value = "tour",required = false)Integer page){
        Page<TourEntity> tourEntityPage;
        if(s.isPresent()){
            tourEntityPage = tourService.findAllByTitleContaining(s.get(), pageable);
        } else {
            if(page == null ){
                page=0;
            }
            pageable = PageRequest.of(page, 5);
            tourEntityPage = tourService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("userGUI/tourlist");
        modelAndView.addObject("tourEntityPage", tourEntityPage);
        return modelAndView;
    }


    @RequestMapping(value="/special")
    public ModelAndView listSpecial(@RequestParam("spec") Optional<String> s, Pageable pageable, @RequestParam(value = "special",required = false)Integer page){
        Page<CityEntity> cityEntityPage;
        if(s.isPresent()){
            cityEntityPage = cityService.findAllByCityNameContaining(s.get(), pageable);
        } else {
            if(page == null ){
                page=0;
            }
            pageable = PageRequest.of(page, 5);
            cityEntityPage = cityService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("userGUI/special");
        modelAndView.addObject("cityEntityPage", cityEntityPage);
        return modelAndView;
    }

    @RequestMapping(value="/resort")
    public ModelAndView listResort(@RequestParam("resort") Optional<String> s, Pageable pageable, @RequestParam(value = "resort",required = false)Integer page){
        Page<CityEntity> resortEntityPage;
        if(s.isPresent()){
            resortEntityPage = cityService.findAllByCityNameContaining(s.get(), pageable);
        } else {
            if(page == null ){
                page=0;
            }
            pageable = PageRequest.of(page, 5);
            resortEntityPage = cityService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("userGUI/resort");
        modelAndView.addObject("resortEntityPage", resortEntityPage);
        return modelAndView;
    }
    @RequestMapping(value="city/resort/{id}")
    public String ResortCity(@PathVariable("id") int id, Model model) {
        model.addAttribute("resortCategory", resortServer.listResortByCity(id));
        model.addAttribute("cityList", cityService.findCityByID(id));
        return "userGUI/resortList";
    }

    @RequestMapping(value="resort/{id}")
    public String CategoryResort(@PathVariable("id") int id, Model model) {
        model.addAttribute("resortCategory", resortServer.findResortByID(id));
        model.addAttribute("resortComment", commentService.getCommentByResort(id));
        model.addAttribute("comments",new CommentEntity());
        return "userGUI/resortCategory";
    }

    @RequestMapping(value="city/special/{id}")
    public String SpecialCity(@PathVariable("id") int id, Model model) {
        model.addAttribute("specialCategory", specialService.listSpecialByCity(id));
        model.addAttribute("cityList", cityService.findCityByID(id));
        return "userGUI/specialList";
    }

    @RequestMapping(value="special/{id}")
    public String CategorySpecial(@PathVariable("id") int id, Model model) {
        model.addAttribute("specialCategory", specialService.findSpecialByID(id));
        model.addAttribute("specialComment", commentService.getCommentBySpecial(id));
        model.addAttribute("comments",new CommentEntity());
        return "userGUI/specialCategory";
    }

    @RequestMapping(value="/city")
    public ModelAndView listCity(@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam(value = "page",required = false)Integer page){
        Page<CityEntity> cityEntityPage;
        if(s.isPresent()){
            cityEntityPage = cityService.findAllByCityNameContaining(s.get(), pageable);
        } else {
            if(page == null ){
                page=0;
            }
            pageable = PageRequest.of(page, 5);
            cityEntityPage = cityService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("userGUI/listcity");
        modelAndView.addObject("cityEntityPage", cityEntityPage);
        return modelAndView;
    }
    @RequestMapping(value="/region")
    public ModelAndView listRegion(@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam(value = "page",required = false)Integer page){
        Page<RegionEntity> regionEntityPage;
        if(s.isPresent()){
            regionEntityPage = regionService.findAllByRegionNameContaining(s.get(), pageable);
        } else {
            if(page == null ){
                page=0;
            }
            pageable = PageRequest.of(page, 5);
            regionEntityPage = regionService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("userGUI/region");
        modelAndView.addObject("regionEntityPage", regionEntityPage);
        return modelAndView;
    }

    @RequestMapping(value="region/{id}")
    public String CategoryRegion(@PathVariable("id") int id, Model model) {
        model.addAttribute("regionCategory", regionService.findRegionByID(id));
        model.addAttribute("tourByRegion", tourService.findTourEntityByRegionTour(id));
        return "userGUI/regionCategory";
    }

}

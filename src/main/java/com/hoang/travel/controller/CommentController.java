package com.hoang.travel.controller;

import com.hoang.travel.convert.CommentConvert;
import com.hoang.travel.entity.CommentEntity;
import com.hoang.travel.entity.ResortEntity;
import com.hoang.travel.entity.SpecialEntity;
import com.hoang.travel.entity.TourEntity;
import com.hoang.travel.model.CommentModel;
import com.hoang.travel.model.ResortModel;
import com.hoang.travel.model.SpecialModel;
import com.hoang.travel.model.TourModel;
import com.hoang.travel.service.ICommentService;
import com.hoang.travel.service.IResortServer;
import com.hoang.travel.service.ISpecialService;
import com.hoang.travel.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @Autowired
    private ITourService tourService;

    @Autowired
    private IResortServer resortServer;

    @Autowired
    private ISpecialService specialService;

    @Autowired
    private CommentConvert commentConvert;

    @RequestMapping(value = "/tour/add/{id}", method = RequestMethod.POST)
    public String add(@ModelAttribute("users") CommentModel commentEntity, @PathVariable("id")Integer id){
        TourModel tourEntity = tourService.findTourByID(id);
        commentEntity.setIdTour(tourEntity);
        commentEntity.setContent(commentEntity.getContent());
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        commentEntity.setCreatedAt(timestamp);
        commentService.addComment(commentConvert.dtoToEntity(commentEntity));
        return "redirect:/tour/"+id+"";
    }

    @RequestMapping(value = "/special/add/{id}", method = RequestMethod.POST)
    public String addCSpecial(@ModelAttribute("comments") CommentModel commentEntity, @PathVariable("id")Integer id){
        SpecialModel specialEntity = specialService.findSpecialByID(id);
        commentEntity.setIdSpecial(specialEntity);
        commentEntity.setContent(commentEntity.getContent());
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        commentEntity.setCreatedAt(timestamp);
        commentService.addComment(commentConvert.dtoToEntity(commentEntity));
        return "redirect:/special/"+id+"";
    }

    @RequestMapping(value = "/resort/add/{id}", method = RequestMethod.POST)
    public String addResort(@ModelAttribute("comments") CommentModel commentEntity, @PathVariable("id")Integer id){
        ResortModel resortEntity = resortServer.findResortByID(id);
        commentEntity.setIdResort(resortEntity);
        commentEntity.setContent(commentEntity.getContent());
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        commentEntity.setCreatedAt(timestamp);
        commentService.addComment(commentConvert.dtoToEntity(commentEntity));
        return "redirect:/resort/"+id+"";
    }
}

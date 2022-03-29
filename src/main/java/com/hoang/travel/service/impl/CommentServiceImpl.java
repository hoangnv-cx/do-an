package com.hoang.travel.service.impl;

import com.hoang.travel.entity.CommentEntity;
import com.hoang.travel.entity.UserEntity;
import com.hoang.travel.repository.CommentRepository;
import com.hoang.travel.service.ICommentService;
import com.hoang.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IUserService userService;
    @Override
    public List<CommentEntity> getCommentByTour(int id) {
        return commentRepository.findByIdTour(id);
    }

    @Override
    public List<CommentEntity> getCommentBySpecial(int id) {
        return commentRepository.findByIdSpecial(id);
    }

    @Override
    public List<CommentEntity> getCommentByResort(int id) {
        return commentRepository.findByIdResort(id);
    }

    @Override
    public CommentEntity addComment(CommentEntity commentEntity) {
        UserEntity userEntity = userService.findByName();
        commentEntity.setUserComment(userEntity);
        return commentRepository.save(commentEntity);
    }
}

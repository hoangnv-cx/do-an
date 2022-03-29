package com.hoang.travel.service;

import com.hoang.travel.entity.CommentEntity;

import java.util.List;

public interface ICommentService {
    List<CommentEntity> getCommentByTour(int id);
    List<CommentEntity> getCommentBySpecial(int id);
    List<CommentEntity> getCommentByResort(int id);
    CommentEntity addComment(CommentEntity commentEntity);
}

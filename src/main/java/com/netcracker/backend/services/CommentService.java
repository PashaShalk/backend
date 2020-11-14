package com.netcracker.backend.services;

import com.netcracker.backend.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByPostID(Long postID);

    void sendComment(Long postID, Long userID, String text);
}

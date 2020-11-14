package com.netcracker.backend.controller;

import com.netcracker.backend.model.Comment;
import com.netcracker.backend.services.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    private final CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{postID}")
    public List<Comment> getCommentByPostID(@PathVariable Long postID) {
        return commentService.getCommentByPostID(postID);
    }

    @PostMapping("/post/{postID}/user/{userID}")
    public void sendComment(@RequestBody String text,
                            @PathVariable Long postID,
                            @PathVariable Long userID) {
        commentService.sendComment(postID, userID, text);
    }
}

package com.netcracker.backend.services.impl;

import com.netcracker.backend.model.Comment;
import com.netcracker.backend.repositories.CommentRepository;
import com.netcracker.backend.repositories.PostRepository;
import com.netcracker.backend.repositories.UserRepository;
import com.netcracker.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Comment> getCommentByPostID(Long postID) {
        return commentRepository.findByPostID(postID);
    }

    @Override
    public void sendComment(Long postID, Long userID, String text) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setAuthor(userRepository.findByID(userID).get());
        comment.setPost(postRepository.findByID(postID));
        comment.setDate(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
        commentRepository.save(comment);
    }
}

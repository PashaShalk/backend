package com.netcracker.backend.services.impl;

import com.netcracker.backend.model.Dislike;
import com.netcracker.backend.model.Like;
import com.netcracker.backend.model.ReactionsData;
import com.netcracker.backend.repositories.DislikeRepository;
import com.netcracker.backend.repositories.LikeRepository;
import com.netcracker.backend.repositories.PostRepository;
import com.netcracker.backend.repositories.UserRepository;
import com.netcracker.backend.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public ReactionServiceImpl(LikeRepository likeRepository, DislikeRepository dislikeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.dislikeRepository = dislikeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public ReactionsData getReactionsData(Long userID, Long postID) {
        return ReactionsData.builder()
                .likes(likeRepository.countByPostID(postID))
                .dislikes(dislikeRepository.countByPostID(postID))
                .likeStatus(likeRepository.findByPostIDAndAuthorID(postID, userID).isPresent())
                .dislikeStatus(dislikeRepository.findByPostIDAndAuthorID(postID, userID) != null)
                .build();
    }

    @Override
    public ReactionsData changeLikeStatus(Long userID, Long postID, Boolean likeStatus) {
        if (likeStatus) {
            likeRepository.delete(likeRepository.findByPostIDAndAuthorID(postID, userID).get());
        } else {
            Like like = new Like();
            like.setAuthor(userRepository.findByID(userID).get());
            like.setPost(postRepository.findByID(postID));
            like.setDate(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            likeRepository.save(like);
        }
        return ReactionsData.builder()
                .likes(likeRepository.countByPostID(postID))
                .dislikes(dislikeRepository.countByPostID(postID))
                .likeStatus(likeRepository.findByPostIDAndAuthorID(postID, userID).isPresent())
                .dislikeStatus(dislikeRepository.findByPostIDAndAuthorID(postID, userID) != null)
                .build();
    }

    @Override
    public ReactionsData changeDislikeStatus(Long userID, Long postID, Boolean dislikeStatus) {
        if (dislikeStatus) {
            dislikeRepository.delete(dislikeRepository.findByPostIDAndAuthorID(postID, userID));
        } else {
            Dislike dislike = new Dislike();
            dislike.setAuthor(userRepository.findByID(userID).get());
            dislike.setPost(postRepository.findByID(postID));
            dislike.setDate(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            dislikeRepository.save(dislike);
        }
        return ReactionsData.builder()
                .likes(likeRepository.countByPostID(postID))
                .dislikes(dislikeRepository.countByPostID(postID))
                .likeStatus(likeRepository.findByPostIDAndAuthorID(postID, userID).isPresent())
                .dislikeStatus(dislikeRepository.findByPostIDAndAuthorID(postID, userID) != null)
                .build();
    }

    @Override
    public ReactionsData changeLikeAndDislikeStatuses(Long userID, Long postID, Boolean likeStatus, Boolean dislikeStatus) {
        if (likeStatus && !dislikeStatus) {
            likeRepository.delete(likeRepository.findByPostIDAndAuthorID(postID, userID).get());
            Dislike dislike = new Dislike();
            dislike.setAuthor(userRepository.findByID(userID).get());
            dislike.setPost(postRepository.findByID(postID));
            dislike.setDate(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            dislikeRepository.save(dislike);
        } else {
            dislikeRepository.delete(dislikeRepository.findByPostIDAndAuthorID(postID, userID));
            Like like = new Like();
            like.setAuthor(userRepository.findByID(userID).get());
            like.setPost(postRepository.findByID(postID));
            like.setDate(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            likeRepository.save(like);
        }
        return ReactionsData.builder()
                .likes(likeRepository.countByPostID(postID))
                .dislikes(dislikeRepository.countByPostID(postID))
                .likeStatus(likeRepository.findByPostIDAndAuthorID(postID, userID).isPresent())
                .dislikeStatus(dislikeRepository.findByPostIDAndAuthorID(postID, userID) != null)
                .build();
    }
}

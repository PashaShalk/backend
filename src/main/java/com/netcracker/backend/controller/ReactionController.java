package com.netcracker.backend.controller;

import com.netcracker.backend.model.ReactionsData;
import com.netcracker.backend.services.impl.ReactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reactions")
public class ReactionController {

    private final ReactionServiceImpl reactionService;

    @Autowired
    public ReactionController(ReactionServiceImpl reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping("/user/{userID}/post/{postID}")
    public ReactionsData getReactionData(@PathVariable Long userID,
                                         @PathVariable Long postID) {
        return reactionService.getReactionsData(userID, postID);
    }

    @GetMapping("/user/{userID}/post/{postID}/likeStatus/{likeStatus}")
    public ReactionsData changeLikeStatus(@PathVariable Long userID,
                                          @PathVariable Long postID,
                                          @PathVariable Boolean likeStatus) {
        return reactionService.changeLikeStatus(userID, postID, likeStatus);
    }

    @GetMapping("/user/{userID}/post/{postID}/dislikeStatus/{dislikeStatus}")
    public ReactionsData changeDislikeStatus(@PathVariable Long userID,
                                             @PathVariable Long postID,
                                             @PathVariable Boolean dislikeStatus) {
        return reactionService.changeDislikeStatus(userID, postID, dislikeStatus);
    }

    @GetMapping("/user/{userID}/post/{postID}/likeStatus/{likeStatus}/dislikeStatus/{dislikeStatus}")
    public ReactionsData changeLikeAndDislikeStatuses(@PathVariable Long userID,
                                                      @PathVariable Long postID,
                                                      @PathVariable Boolean dislikeStatus,
                                                      @PathVariable Boolean likeStatus) {
        return reactionService.changeLikeAndDislikeStatuses(userID, postID, likeStatus, dislikeStatus);
    }
}

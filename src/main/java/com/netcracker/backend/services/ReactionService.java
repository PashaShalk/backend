package com.netcracker.backend.services;

import com.netcracker.backend.model.ReactionsData;

public interface ReactionService {
    ReactionsData getReactionsData(Long userID, Long postID);

    ReactionsData changeLikeStatus(Long userID, Long postID, Boolean likeStatus);

    ReactionsData changeDislikeStatus(Long userID, Long postID, Boolean dislikeStatus);

    ReactionsData changeLikeAndDislikeStatuses(Long userID, Long postID, Boolean likeStatus, Boolean dislikeStatus);
}

package com.netcracker.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReactionsData {
    private int likes;
    private int dislikes;
    private boolean likeStatus;
    private boolean dislikeStatus;
}

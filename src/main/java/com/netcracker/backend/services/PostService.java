package com.netcracker.backend.services;

import com.netcracker.backend.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Long ID, String description);

    List<Post> getUserPosts(String nickname, Integer page, Integer count);

    List<Post> getUserFeed(Long ID, Integer page, Integer count);

    List<Post> getAllPostsInTwelveHours(Integer page, Integer count);

    List<Post> getPostsByHashtag(String hashtag, Integer page, Integer count);

    void deletePost(Long ID);
}

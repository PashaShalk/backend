package com.netcracker.backend.controller;

import com.netcracker.backend.model.Post;
import com.netcracker.backend.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{ID}/newpost")
    public Post createPost(@PathVariable Long ID,
                           @RequestBody(required = false) String description) {
        return postService.createPost(ID, description);
    }

    @GetMapping("user/{nickname}/page/{page}/count/{count}")
    public List<Post> getUserPosts(@PathVariable String nickname,
                                   @PathVariable Integer page,
                                   @PathVariable Integer count) {
        return postService.getUserPosts(nickname, page, count);
    }

    @GetMapping("/feed/user/{ID}/page/{page}/count/{count}")
    public List<Post> getUserPosts(@PathVariable Long ID,
                                   @PathVariable Integer page,
                                   @PathVariable Integer count) {
        return postService.getUserFeed(ID, page, count);
    }

    @GetMapping("/feed/page/{page}/count/{count}")
    public List<Post> getAllPostsIn12Hours(@PathVariable Integer page,
                                           @PathVariable Integer count) {
        return postService.getAllPostsInTwelveHours(page, count);
    }

    @GetMapping("/feed/hashtag/{hashtag}/page/{page}/count/{count}")
    public List<Post> getPostsByHashtag(@PathVariable String hashtag,
                                        @PathVariable Integer page,
                                        @PathVariable Integer count) {
        return postService.getPostsByHashtag(hashtag, page, count);
    }

    @GetMapping("/delete/{ID}")
    public void blockUser(@PathVariable Long ID) {
        postService.deletePost(ID);
    }
}

package com.netcracker.backend.services.impl;

import com.netcracker.backend.model.Hashtag;
import com.netcracker.backend.model.Post;
import com.netcracker.backend.model.User;
import com.netcracker.backend.repositories.HashtagRepository;
import com.netcracker.backend.repositories.PostRepository;
import com.netcracker.backend.repositories.UserRepository;
import com.netcracker.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PostServiceImpl implements PostService {

    private static final String HASHTAG_TEMPLATE = "(\\#\\w+\\b)(?!;)";
    private static final int HOURS_AGO = 12 * 60 * 60 * 1000;

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, HashtagRepository hashtagRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.hashtagRepository = hashtagRepository;
    }

    @Override
    public Post createPost(Long ID, String description) {
        Optional<User> user = userRepository.findByID(ID);

        if (user.isPresent()) {
            Post post = new Post();
            post.setAuthor(user.get());
            post.setDate(new Timestamp(new Date(System.currentTimeMillis()).getTime()));

            if (description != null) {
                post.setDescription(description);
                Pattern pattern = Pattern.compile(HASHTAG_TEMPLATE);
                Matcher matcher = pattern.matcher(post.getDescription());

                while (matcher.find()) {
                    String tag = matcher.group().split("#")[1];
                    Optional<Hashtag> hashtag = hashtagRepository.getByText(tag);
                    if (hashtag.isPresent()) {
                        post.getHashtags().add(hashtag.get());
                    } else {
                        Hashtag ht = new Hashtag();
                        ht.setText(tag);
                        post.getHashtags().add(hashtagRepository.save(ht));
                    }
                }
            }
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public List<Post> getUserPosts(String nickname, Integer page, Integer count) {
        return postRepository.findAllByAuthorNickname(nickname,
                PageRequest.of(page, count, Sort.by(Sort.Direction.DESC, "date")));
    }

    @Override
    public List<Post> getUserFeed(Long ID, Integer page, Integer count) {
        return postRepository.findUserFeed(ID,
                PageRequest.of(page, count, Sort.by(Sort.Direction.DESC, "date")));
    }

    @Override
    public List<Post> getAllPostsInTwelveHours(Integer page, Integer count) {
        return postRepository.findAllByDateAfter(new Date(System.currentTimeMillis() - HOURS_AGO),
                PageRequest.of(page, count, Sort.by(Sort.Direction.DESC, "date")));
    }

    @Override
    public List<Post> getPostsByHashtag(String hashtag, Integer page, Integer count) {
        return postRepository.findAllByHashtag(hashtag,
                PageRequest.of(page, count, Sort.by(Sort.Direction.DESC, "date")));
    }

    @Override
    public void deletePost(Long ID) {
        postRepository.delete(postRepository.findByID(ID));
    }
}

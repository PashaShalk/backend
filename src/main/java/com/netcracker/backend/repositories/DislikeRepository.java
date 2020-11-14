package com.netcracker.backend.repositories;

import com.netcracker.backend.model.Dislike;
import org.springframework.data.repository.CrudRepository;

public interface DislikeRepository extends CrudRepository<Dislike, Long> {
    Integer countByPostID(Long postID);
    Dislike findByPostIDAndAuthorID(Long postID, Long authorID);
}

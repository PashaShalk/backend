package com.netcracker.backend.repositories;

import com.netcracker.backend.model.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    Integer countByPostID(Long postID);
    Optional<Like> findByPostIDAndAuthorID(Long postID, Long authorID);
}

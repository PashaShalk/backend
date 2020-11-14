package com.netcracker.backend.repositories;

import com.netcracker.backend.model.Hashtag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HashtagRepository extends CrudRepository<Hashtag, Long> {
    Optional<Hashtag> getByText(String text);
}

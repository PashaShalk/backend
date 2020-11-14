package com.netcracker.backend.repositories;

import com.netcracker.backend.model.User;
import com.netcracker.backend.model.UserRole;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByID(long ID);

    List<User> findByRole(UserRole role, Pageable pageable);

    Long countUsersByRole(UserRole role);

    @Query(value = "SELECT count(*) from mo_user_subscriptions WHERE subscriber_id = ?1",
            nativeQuery = true)
    Integer countSubscriptions(Long ID);

    @Query(value = "SELECT count(*) from mo_user_subscriptions WHERE channel_id = ?1",
            nativeQuery = true)
    Integer countSubscribers(Long ID);

    @Query(value = "SELECT count(*) from mo_user_subscriptions WHERE subscriber_id = ?1 and channel_id = ?2",
            nativeQuery = true)
    Integer findBySubscriberAndSubscription(Long subscriberID, Long channelID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO mo_user_subscriptions(subscriber_id, channel_id) VALUES(?1, ?2)",
            nativeQuery = true)
    void subscribe(Long subscriberID, Long channelID);

    @Modifying
    @Transactional
    @Query(value = "DELETE from mo_user_subscriptions mo WHERE mo.subscriber_id = ?1 and mo.channel_id = ?2",
            nativeQuery = true)
    void unsubscribe(Long subscriberID, Long channelID);
}

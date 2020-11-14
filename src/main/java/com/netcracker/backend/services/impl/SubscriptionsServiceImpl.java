package com.netcracker.backend.services.impl;

import com.netcracker.backend.model.SubscriptionsData;
import com.netcracker.backend.repositories.UserRepository;
import com.netcracker.backend.services.SubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {

    private final UserRepository userRepository;

    @Autowired
    public SubscriptionsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SubscriptionsData getSubscriptionsData(Long userID, Long authorizedUserID) {
        return SubscriptionsData.builder()
                .subscribers(userRepository.countSubscribers(userID))
                .subscriptions(userRepository.countSubscriptions(userID))
                .isSubscribe(userRepository.findBySubscriberAndSubscription(userID, authorizedUserID) == 1)
                .build();
    }

    @Override
    public SubscriptionsData subscribe(Long userID, Long authorizedUserID) {
        userRepository.subscribe(userID, authorizedUserID);
        return getSubscriptionsData(userID, authorizedUserID);
    }

    @Override
    public SubscriptionsData unsubscribe(Long userID, Long authorizedUserID) {
        userRepository.unsubscribe(userID, authorizedUserID);
        return getSubscriptionsData(userID, authorizedUserID);
    }
}

package com.netcracker.backend.services;

import com.netcracker.backend.model.SubscriptionsData;

public interface SubscriptionsService {
    SubscriptionsData getSubscriptionsData(Long userID, Long authorizedUserID);

    SubscriptionsData subscribe(Long userID, Long authorizedUserID);

    SubscriptionsData unsubscribe(Long userID, Long authorizedUserID);
}

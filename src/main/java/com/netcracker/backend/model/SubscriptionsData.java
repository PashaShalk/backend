package com.netcracker.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionsData {
    private int subscribers;
    private int subscriptions;
    private boolean isSubscribe;
}

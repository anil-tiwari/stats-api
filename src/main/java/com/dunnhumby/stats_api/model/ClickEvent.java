package com.dunnhumby.stats_api.model;

import lombok.Data;

@Data
public class ClickEvent {
    // The campaign ID associated with the click event
    private String campaignId;
    // The client ID for identifying data for the client
    private String clientId;
    // The user ID of the user who performed the click
    private String userId;
    // The product ID associated with the click event
    private String productId;
    // The timestamp of the click event (in Unix time format)
    private Long clickTimestamp;
}

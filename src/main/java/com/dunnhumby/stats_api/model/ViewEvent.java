package com.dunnhumby.stats_api.model;

import lombok.Data;

@Data
public class ViewEvent {
    // The campaign ID associated with the view event
    private String campaignId;
    // The client ID for identifying data for the client
    private String clientId;
    // The user ID of the user who performed the view
    private String userId;
    // The product ID associated with the view event
    private String productId;
    // The timestamp of the view event (in Unix time format)
    private Long clickTimestamp;
}

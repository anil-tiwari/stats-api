package com.dunnhumby.stats_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventData {
    // The campaign ID associated with the click event
    private String campaignId;

    // The user ID of the user who performed the click
    private String userId;

    // The product ID associated with the click event
    private String productId;

    // The timestamp of the click event (in Unix time format)
    private Long clickTimestamp;


}

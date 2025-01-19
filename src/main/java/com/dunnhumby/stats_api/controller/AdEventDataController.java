package com.dunnhumby.stats_api.controller;

import com.dunnhumby.stats_api.auth.JwtTokenProvider;
import com.dunnhumby.stats_api.config.ClickHouseUtil;
import com.dunnhumby.stats_api.model.EventData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ad/v1/")
public class AdEventDataController {

    @Autowired
    private JwtTokenProvider jwtUtil;


    @GetMapping("{campaignID}/clicks-data")
    @Tag(name = "Get Clicks data by Champaign-Id", description ="This API gives clicks data w.r.t. Champaign ")
    public ResponseEntity<List<EventData>> getClicksData(@PathVariable String campaignID , @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String clientId = null;

        try {
            clientId = jwtUtil.getClientId(token);
            if(clientId == null) {
                return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }

        String sql = "select * from click_events where client_id ="+ clientId +" and campaign_id="+campaignID;
        List<Map<String, Object>> abc = ClickHouseUtil.sqlQuery(sql);

        List<EventData> eventDataList = new ArrayList<>();
        for (Map<String, Object> objectMap : abc) {
            EventData eventData = new EventData();
            eventData.setCampaignId(objectMap.get("campaign_id").toString());
            eventData.setUserId(objectMap.get("user_id").toString());
            eventData.setProductId(objectMap.get("product_id").toString());
            eventData.setClickTimestamp((Long) objectMap.get("click_timestamp"));
            eventDataList.add(eventData);
        }

        return new ResponseEntity<>(eventDataList, HttpStatus.OK);

    }

    @GetMapping("{campaignID}/views-data")
    @Tag(name = "Get View Data by Champaign-Id", description ="This API gives View stats data w.r.t. Champaign ")
    public ResponseEntity<List<EventData>> getViewsData(@PathVariable String campaignID,
                                                     @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String clientId = null;

        try {
            clientId = jwtUtil.getClientId(token);
            if(clientId == null) {
                return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }

        String sql = "select * from view_events where client_id ="+ clientId +" and campaign_id="+campaignID;
        List<Map<String, Object>> views = ClickHouseUtil.sqlQuery(sql);

        List<EventData> eventDataList = new ArrayList<>();
        for (Map<String, Object> objectMap : views) {
            EventData eventData = new EventData();
            eventData.setCampaignId(objectMap.get("campaign_id").toString());
            eventData.setUserId(objectMap.get("user_id").toString());
            eventData.setProductId(objectMap.get("product_id").toString());
            eventData.setClickTimestamp((Long) objectMap.get("click_timestamp"));
            eventDataList.add(eventData);
        }

        return new ResponseEntity<>(eventDataList, HttpStatus.OK);
    }
}



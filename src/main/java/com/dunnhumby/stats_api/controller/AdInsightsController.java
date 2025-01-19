package com.dunnhumby.stats_api.controller;

import com.dunnhumby.stats_api.auth.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ad/v1/")
public class AdInsightsController {

    @Autowired
    private JwtTokenProvider jwtUtil;

    @Autowired
    private ReactiveRedisTemplate<String, String> redisTemplate;

    @GetMapping("{campaignID}/clicks")
    @Tag(name = "Get Clicks Count by Champaign-Id", description ="This API gives clicks count stats data w.r.t. Champaign ")
    public Mono<ResponseEntity<Long>> getClicksCount(@PathVariable String campaignID,
                                     @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String clientId = null;

        try {
            clientId = jwtUtil.getClientId(token);
            if(clientId == null) {
                return Mono.just(ResponseEntity.status(401).build());
            }
        } catch (Exception e) {
            return Mono.just(ResponseEntity.status(401).build());
        }
        String redisKey = "ad_stats_click" + clientId +"_"  + campaignID;

        ReactiveHashOperations<String, String, String> HashOps = redisTemplate.opsForHash();

        return HashOps.get(redisKey, campaignID)
                .flatMap(value -> Mono.just(ResponseEntity.ok(Long.parseLong(value))))
                .switchIfEmpty(Mono.just(ResponseEntity.ok(0L)));

    }

    @GetMapping("{campaignID}/views")
    @Tag(name = "Get View Count by Champaign-Id", description ="This API gives View stats data w.r.t. Champaign ")
    public Mono<ResponseEntity<Long>> getViewsCount(@PathVariable String campaignID,
                                                     @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String clientId = null;
        try {
            clientId = jwtUtil.getClientId(token);
            if(clientId == null) {
                return Mono.just(ResponseEntity.status(401).build());
            }
        } catch (Exception e) {
            return Mono.just(ResponseEntity.status(401).build());
        }
        String redisKey = "ad_stats_view" + clientId +"_"  + campaignID;
        ReactiveHashOperations<String, String, String> HashOps = redisTemplate.opsForHash();


        return HashOps.get(redisKey, campaignID)
                .flatMap(value -> Mono.just(ResponseEntity.ok(Long.parseLong(value))))
                .switchIfEmpty(Mono.just(ResponseEntity.ok(0L)));
    }
}



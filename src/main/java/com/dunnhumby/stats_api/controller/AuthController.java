package com.dunnhumby.stats_api.controller;

import com.dunnhumby.stats_api.auth.JwtTokenProvider;
import com.dunnhumby.stats_api.model.AuthResponse;
import com.dunnhumby.stats_api.model.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtUtil;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody UserIdentity
                                                           userIdentity) {
        String clientId = userIdentity.getClientId();
        String clientName = userIdentity.getClientName();
        Authentication authentication = new UsernamePasswordAuthenticationToken(clientId, clientName);

        /*
        Use mysql or some other database to verify the client is valid or not
         */
        if (authentication.isAuthenticated()) { // Replace with actual authentication logic

            String token = jwtUtil.createToken(authentication);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            return Mono.just(ResponseEntity.ok(authResponse));
        }

        return Mono.just(ResponseEntity.status(401).build());
    }
}

package com.programming.productservice.service;

import com.programming.productservice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {
    @Autowired
    private WebClient.Builder webClient;

    public User getUser(String userId) {
        return this.webClient.build().get()
                .uri("/api/users/{id}", userId)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

}

package com.ticket_service.ticket_service.service;

import com.ticket_service.ticket_service.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService{
    private final WebClient webClient;
    @Value("${external.user-service.base-url.host}")
    private String baseUrl;
    @Value("${external.user-service.base-url.valid-token-path}")
    private String validTokenPath;

    public TicketServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public UserResponseDTO validateToken(String token){
        return webClient.post()
                .uri(baseUrl + validTokenPath)
                .bodyValue(Map.of("token", token))
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();
    }
}

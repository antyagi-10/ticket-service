package com.ticket_service.ticket_service.controller;

import com.ticket_service.ticket_service.dto.UserResponseDTO;
import com.ticket_service.ticket_service.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/validateToken")
    public ResponseEntity<UserResponseDTO> validateToken( @RequestHeader("Authorization") String authorizationHeader){
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }
        String token = authorizationHeader.substring(7);
        ticketService.validateToken(token);
        UserResponseDTO user =  ticketService.validateToken(token);
        return ResponseEntity.ok(user);
    }
}

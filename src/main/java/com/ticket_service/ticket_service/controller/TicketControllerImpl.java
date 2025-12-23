package com.ticket_service.ticket_service.controller;

import com.ticket_service.ticket_service.dto.TicketRequestDTO;
import com.ticket_service.ticket_service.dto.UserResponseDTO;
import com.ticket_service.ticket_service.entity.TicketEntity;
import com.ticket_service.ticket_service.service.TicketServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketControllerImpl implements TicketController {

    private final TicketServiceImpl ticketService;

    public TicketControllerImpl(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public ResponseEntity<UserResponseDTO> validateToken(
            @RequestHeader("Authorization") String authorizationHeader){
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authorizationHeader.substring(7);
        UserResponseDTO user =  ticketService.validateToken(token);
        return ResponseEntity.ok(user);
    }

}

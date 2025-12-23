package com.ticket_service.ticket_service.controller;

import com.ticket_service.ticket_service.dto.TicketRequestDTO;
import com.ticket_service.ticket_service.dto.TicketResponseDTO;
import com.ticket_service.ticket_service.dto.UserResponseDTO;
import com.ticket_service.ticket_service.entity.TicketEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface TicketController {
    @PostMapping("/validateToken")
    ResponseEntity<UserResponseDTO> validateToken(@RequestHeader("Authorization") String authorizationHeader);
    @PostMapping("/createTickets")
    ResponseEntity<TicketResponseDTO> createTicket(@RequestHeader("Authorization") String authorizationHeader, @RequestBody TicketRequestDTO request);
}

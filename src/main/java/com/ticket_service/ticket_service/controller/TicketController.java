package com.ticket_service.ticket_service.controller;

import com.ticket_service.ticket_service.dto.TicketRequestDTO;
import com.ticket_service.ticket_service.dto.TicketResponseDTO;
import com.ticket_service.ticket_service.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TicketController {
    @PostMapping("/validateToken")
    ResponseEntity<UserResponseDTO> validateToken(@RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/createTickets")
    ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody  TicketRequestDTO request,@RequestHeader("Authorization") String authorizationHeader);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteTicket(@PathVariable Integer id, @RequestHeader("Authorization") String authorizationHeader);
}

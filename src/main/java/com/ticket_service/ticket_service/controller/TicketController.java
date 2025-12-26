package com.ticket_service.ticket_service.controller;

import com.ticket_service.ticket_service.dto.*;
import com.ticket_service.ticket_service.entity.TicketEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface TicketController {
    @PostMapping("/validateToken")
    ResponseEntity<UserResponseDTO> validateToken(
            @RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/createTickets")
    ResponseEntity<TicketResponseDTO> createTicket(
            @Valid @RequestBody  TicketRequestDTO request,
            @RequestHeader("Authorization") String authorizationHeader);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteTicket(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String authorizationHeader);

    @GetMapping("/getAllTickets")
    ResponseEntity<List<TicketEntity>> getAllTickets(
            @RequestHeader("Authorization") String authorizationHeader);

    @GetMapping("/{id}")
    ResponseEntity<TicketEntity> getTicketById(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/{ticketId}/addComment")
    ResponseEntity<CommentResponseDTO> addComment(
            @PathVariable Integer ticketId,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CommentRequestDTO request
    );

    @PatchMapping("{ticketId}/{commentId}/updateComment")
    ResponseEntity<CommentResponseDTO> updateComment(
            @PathVariable Integer ticketId,
            @PathVariable Integer commentId,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CommentRequestDTO request
    );

}

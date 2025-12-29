package com.ticket_service.ticket_service.controller;

import com.ticket_service.ticket_service.dto.*;
import com.ticket_service.ticket_service.entity.Comment;
import com.ticket_service.ticket_service.entity.TicketEntity;
import com.ticket_service.ticket_service.mapper.TicketMapper;
import com.ticket_service.ticket_service.payload.TicketPayload;
import com.ticket_service.ticket_service.publisher.TicketPublisher;
import com.ticket_service.ticket_service.service.TicketServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketControllerImpl implements TicketController {

    private final TicketServiceImpl ticketService;
    private final TicketPublisher ticketPublisher;

    public TicketControllerImpl(TicketServiceImpl ticketService,
                                TicketPublisher ticketPublisher) {
        this.ticketService = ticketService;
        this.ticketPublisher = ticketPublisher;
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

    @Override
    public ResponseEntity<TicketResponseDTO> createTicket(
            @Valid @RequestBody  TicketRequestDTO request,
            @RequestHeader("Authorization") String authorizationHeader){
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authorizationHeader.substring(7);
        TicketEntity ticket = ticketService.createTicket(request, token);
        PayloadDTO.Payload inner = PayloadDTO.Payload.builder()
                .id(ticket.getId())
                .createdAt(ticket.getCreated_at())
                .updatedAt(ticket.getUpdated_at())
                .build();
        PayloadDTO payloadDTO = PayloadDTO.builder()
                .operation("POST")
                .type("ticket")
                .payload(inner)
                .build();
        TicketPayload event = TicketPayload.builder()
                        .event("Ticket Created")
                                .timestamp(LocalTime.now())
                                        .body(payloadDTO)
                                                .build();
        ticketPublisher.sendEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(TicketMapper.toDto(ticket));
    }

    @Override
    public ResponseEntity<String> deleteTicket(@PathVariable Integer id,
                                         @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authorizationHeader.substring(7);
        boolean deleted = ticketService.deleteTicket(id, token);
        return ResponseEntity.ok("Ticket deleted successfully");
    }

    @Override
    public ResponseEntity<List<TicketEntity>> getAllTickets( @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authorizationHeader.substring(7);
        return ResponseEntity.ok(ticketService.getAllTickets(token));
    }

    @Override
    public ResponseEntity<TicketEntity> getTicketById(@PathVariable Integer id, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authorizationHeader.substring(7);
        TicketEntity entry = ticketService.getTicketById(id, token);
        return ResponseEntity.ok(entry);
    }

    @Override
    public ResponseEntity<CommentResponseDTO> addComment(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CommentRequestDTO request
    ) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authorizationHeader.substring(7);
        Comment comment = ticketService.addComment(token, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(TicketMapper.toDto(comment));
    }

    @Override
    public ResponseEntity<CommentResponseDTO> updateComment(
            @PathVariable Integer commentId,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CommentRequestDTO request
    ){
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authorizationHeader.substring(7);
        Comment comment = ticketService.updateComment(commentId, token, request);
        return ResponseEntity.ok(TicketMapper.toDto(comment));
    }

}


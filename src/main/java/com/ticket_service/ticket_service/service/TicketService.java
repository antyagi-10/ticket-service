package com.ticket_service.ticket_service.service;

import com.ticket_service.ticket_service.dto.TicketRequestDTO;
import com.ticket_service.ticket_service.dto.UserResponseDTO;
import com.ticket_service.ticket_service.entity.TicketEntity;

public interface TicketService {
    UserResponseDTO validateToken(String token);
    TicketEntity createTicket(TicketRequestDTO request, String token);
    boolean deleteTicket(Integer id, String token);
}

package com.ticket_service.ticket_service.service;

import com.ticket_service.ticket_service.dto.UserResponseDTO;

public interface TicketService {
    UserResponseDTO validateToken(String token);
}

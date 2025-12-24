package com.ticket_service.ticket_service.service;

import com.ticket_service.ticket_service.dto.TicketRequestDTO;
import com.ticket_service.ticket_service.dto.UserResponseDTO;
import com.ticket_service.ticket_service.entity.TicketEntity;
import com.ticket_service.ticket_service.exception.TicketNotFoundException;
import com.ticket_service.ticket_service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;
    private final WebClient webClient;
    @Value("${external.user-service.base-url.host}")
    private String baseUrl;
    @Value("${external.user-service.base-url.valid-token-path}")
    private String validTokenPath;

    public TicketServiceImpl(TicketRepository ticketRepository, WebClient webClient) {
        this.ticketRepository = ticketRepository;
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

    @Override
    public TicketEntity createTicket(TicketRequestDTO request, String token) {
        UserResponseDTO user = validateToken(token);
        TicketEntity ticket = new TicketEntity();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setStatus(TicketEntity.Status.Open);
        ticket.setCreated_by(user.getId());
        ticket.setAssigned_to(null);
        return ticketRepository.save(ticket);
    }

    @Override
    public boolean deleteTicket(Integer id, String token) {
        UserResponseDTO user = validateToken(token);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        if(ticketRepository.findById(id).isEmpty()){
            throw new TicketNotFoundException("Ticket not Found");
        }
        ticketRepository.deleteById(id);
        return true;
    }

    @Override
    public List<TicketEntity> getAllTickets( String token) {
        UserResponseDTO user = validateToken(token);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        boolean isAdmin = user.getRole().equals("Admin");
        if (!isAdmin) {
            throw new AccessDeniedException("You are not allowed to access this ticket");
        }
        return ticketRepository.findAll();
    }

    @Override
    public TicketEntity getTicketById(Integer id, String token) {
        TicketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("User not found"));
        UserResponseDTO user = validateToken(token);
        boolean isCreator = ticket.getCreated_by().equals(user.getId());
        boolean isAssignee = ticket.getAssigned_to() != null &&
                ticket.getAssigned_to().equals(user.getId());
        boolean isAdmin = user.getRole().equals("Admin");

        if (!(isCreator || isAssignee || isAdmin)) {
            throw new AccessDeniedException("You are not allowed to access this ticket");
        }
        return ticketRepository.findById(id).get();
    }

}

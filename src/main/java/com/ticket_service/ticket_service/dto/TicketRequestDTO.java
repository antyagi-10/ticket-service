package com.ticket_service.ticket_service.dto;

import com.ticket_service.ticket_service.entity.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDTO {
    private String title;
    private String description;
    private TicketEntity.Priority priority;
}

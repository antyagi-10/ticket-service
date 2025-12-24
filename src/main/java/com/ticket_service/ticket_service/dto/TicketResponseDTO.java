package com.ticket_service.ticket_service.dto;

import com.ticket_service.ticket_service.entity.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {
    private Integer id;
    private String title;
    private TicketEntity.Priority priority;
    private TicketEntity.Status status;
    private Integer created_by;
    private Integer assigned_to;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

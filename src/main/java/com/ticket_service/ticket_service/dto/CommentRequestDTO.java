package com.ticket_service.ticket_service.dto;

import lombok.Data;

@Data
public class CommentRequestDTO {
    private Integer ticketId;
    private String comment;
}

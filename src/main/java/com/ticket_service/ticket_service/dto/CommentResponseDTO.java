package com.ticket_service.ticket_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private String comment;
    private Integer commented_by;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

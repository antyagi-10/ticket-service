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
    private Integer commentedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

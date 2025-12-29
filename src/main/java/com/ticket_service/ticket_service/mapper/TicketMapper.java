package com.ticket_service.ticket_service.mapper;

import com.ticket_service.ticket_service.dto.CommentResponseDTO;
import com.ticket_service.ticket_service.dto.TicketResponseDTO;
import com.ticket_service.ticket_service.entity.Comment;
import com.ticket_service.ticket_service.entity.TicketEntity;

public class TicketMapper {
    public static TicketResponseDTO toDto(TicketEntity ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCreated_by(),
                ticket.getAssigned_to(),
                ticket.getCreated_at(),
                ticket.getUpdated_at()
        );
    }
    public static CommentResponseDTO toDto(Comment comment) {
        return new CommentResponseDTO(
                comment.getComment(),
                comment.getCommentedBy(),
                comment.getCommenterEmail(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }


}

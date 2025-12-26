package com.ticket_service.ticket_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId" , nullable = false)
    private Integer id;

    @Column(name = "comment", nullable = false )
    private String comment;

    @Column(name = "commentedBy", nullable = false )
    private Integer commentedBy;

    @ManyToOne
    @JoinColumn(name = "ticketId", nullable = false)
    @JsonIgnore
    private TicketEntity ticket;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

}

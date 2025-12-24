package com.ticket_service.ticket_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id" , nullable = false)
    private Integer id;

    @Column(name = "comment", nullable = false )
    private String comment;

    @Column(name = "commented_by", nullable = false )
    private Integer commented_by;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketEntity ticket;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}

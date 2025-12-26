package com.ticket_service.ticket_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tickets")
public class TicketEntity {

    public enum Priority{
            Low,
            Medium,
            High
        }

    public enum Status{
        Open,
        Assigned,
        Resolved
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id" , nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false )
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "created_by")
    private Integer created_by;

    @Column(name = "assigned_to")
    private Integer assigned_to;

    @OneToMany(mappedBy = "ticket",  cascade = CascadeType.ALL)
    private List<Comment> comment;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}

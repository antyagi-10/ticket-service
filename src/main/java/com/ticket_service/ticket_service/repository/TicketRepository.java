package com.ticket_service.ticket_service.repository;

import com.ticket_service.ticket_service.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

}

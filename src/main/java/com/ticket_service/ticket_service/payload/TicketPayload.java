package com.ticket_service.ticket_service.payload;


import com.ticket_service.ticket_service.dto.PayloadDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketPayload{
    private String event;
    private LocalTime timestamp;
    private PayloadDTO body;
}

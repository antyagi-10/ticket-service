package com.ticket_service.ticket_service.dto;

import com.ticket_service.ticket_service.entity.TicketEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDTO {
    @NotNull(message = "Title can not be null")
    @Size(max =20, message = "Title can be of 20 characters only")
    private String title;
    @NotNull(message = "Description can not be null")
    @Size(max = 225, message = "Description has crossed the limit of 225 characters.")
    private String description;
    @NotNull(message = "Priority can not be null")
    private TicketEntity.Priority priority;
}

package com.tsystems.javaschool.SBB.mapper.interfaces;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.entities.Ticket;

import java.util.List;

public interface TicketMapper {

    TicketDTO toDTO(Ticket ticket);

    Ticket toEntity(TicketDTO ticketDTO);

    List<TicketDTO> toDTOList(List<Ticket> ticketList);

    List<Ticket> toEntityList(List<TicketDTO> ticketDTOList);
}

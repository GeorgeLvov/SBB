package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = {UserMapper.class})
@Component
public interface TicketMapper {

    @Mappings({
            @Mapping(target = "userDTO", source = "ticket.user")
    })
    TicketDTO toDTO(Ticket ticket);


    @Mappings({
            @Mapping(target = "user", source = "ticketDTO.userDTO")
    })
    Ticket toEntity(TicketDTO ticketDTO);

    List<TicketDTO> toDTOList(List<Ticket> ticketList);

    List<Ticket> toEntityList(List<TicketDTO> ticketDTOList);
}

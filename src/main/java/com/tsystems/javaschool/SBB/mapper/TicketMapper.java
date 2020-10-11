package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = {TrainMapper.class, PassengerMapper.class, UserMapper.class, StationMapper.class})
@Component
public interface TicketMapper {

    @Mappings({
            @Mapping(target = "trainDTO", source = "ticket.train"),
            @Mapping(target = "stationFromDTO", source = "ticket.stationFrom"),
            @Mapping(target = "stationToDTO", source = "ticket.stationTo"),
            @Mapping(target = "passengerDTO", source = "ticket.passenger"),
            @Mapping(target = "userDTO", source = "ticket.user")
    })
    TicketDTO toDTO(Ticket ticket);


    @Mappings({
            @Mapping(target = "train", source = "ticketDTO.trainDTO"),
            @Mapping(target = "stationFrom", source = "ticketDTO.stationFromDTO"),
            @Mapping(target = "stationTo", source = "ticketDTO.stationToDTO"),
            @Mapping(target = "passenger", source = "ticketDTO.passengerDTO"),
            @Mapping(target = "user", source = "ticketDTO.userDTO")
    })
    Ticket toEntity(TicketDTO ticketDTO);

    List<TicketDTO> toDTOList(List<Ticket> ticketList);

    List<Ticket> toEntityList(List<TicketDTO> ticketDTOList);
}

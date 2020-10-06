package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.entities.Passenger;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(uses = {TicketMapper.class})
@Component
public interface PassengerMapper {

    Passenger toEntity(PassengerDTO passengerDTO);

    PassengerDTO toDTO(Passenger passenger);

}

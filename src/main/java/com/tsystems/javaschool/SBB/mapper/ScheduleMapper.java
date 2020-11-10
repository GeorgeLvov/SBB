package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = {TrainMapper.class, TripMapper.class, StationMapper.class})
@Component
public interface ScheduleMapper {
    @Mappings({
            @Mapping(target = "tripDTO", source = "schedule.trip"),
            @Mapping(target = "stationFromDTO", source = "schedule.stationFrom"),
            @Mapping(target = "stationToDTO", source = "schedule.stationTo")
    })
    ScheduleDTO toDTO(Schedule schedule);

    @Mappings({
            @Mapping(target = "trip", source = "scheduleDTO.tripDTO"),
            @Mapping(target = "stationFrom", source = "scheduleDTO.stationFromDTO"),
            @Mapping(target = "stationTo", source = "scheduleDTO.stationToDTO")
    })
    Schedule toEntity(ScheduleDTO scheduleDTO);


    List<ScheduleDTO> toDTOList(List<Schedule> scheduleList);

    List<Schedule> toEntityList(List<ScheduleDTO> scheduleDTOList);
}

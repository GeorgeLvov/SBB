package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.entities.Train;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TrainMapper {
    Train toEntity(TrainDTO trainDTO);
    TrainDTO toDTO(Train train);
    List<TrainDTO> toDTOList(List<Train> trainList);
    List<Train> toEntityList(List<TrainDTO> trainDTOList);
}

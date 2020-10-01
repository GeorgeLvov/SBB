package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO.TrainDTOBuilder;
import com.tsystems.javaschool.SBB.entities.Train;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-02T00:08:56+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class TrainMapperImpl implements TrainMapper {

    @Override
    public Train toEntity(TrainDTO trainDTO) {
        if ( trainDTO == null ) {
            return null;
        }

        Train train = new Train();

        train.setId( trainDTO.getId() );
        train.setTrainName( trainDTO.getTrainName() );
        train.setCapacity( trainDTO.getCapacity() );

        return train;
    }

    @Override
    public TrainDTO toDTO(Train train) {
        if ( train == null ) {
            return null;
        }

        TrainDTOBuilder trainDTO = TrainDTO.builder();

        trainDTO.id( train.getId() );
        trainDTO.trainName( train.getTrainName() );
        trainDTO.capacity( train.getCapacity() );

        return trainDTO.build();
    }

    @Override
    public List<TrainDTO> toDTOList(List<Train> trainList) {
        if ( trainList == null ) {
            return null;
        }

        List<TrainDTO> list = new ArrayList<TrainDTO>( trainList.size() );
        for ( Train train : trainList ) {
            list.add( toDTO( train ) );
        }

        return list;
    }

    @Override
    public List<Train> toEntityList(List<TrainDTO> trainDTOList) {
        if ( trainDTOList == null ) {
            return null;
        }

        List<Train> list = new ArrayList<Train>( trainDTOList.size() );
        for ( TrainDTO trainDTO : trainDTOList ) {
            list.add( toEntity( trainDTO ) );
        }

        return list;
    }
}

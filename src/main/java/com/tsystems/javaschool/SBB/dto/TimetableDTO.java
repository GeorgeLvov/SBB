package com.tsystems.javaschool.SBB.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDTO {

    private String trainName;

    private String stationFrom;

    private String stationTo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+3")
    private Date departureTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+3")
    private Date arrivalTime;

    private int delay;

    private boolean canceled;

}


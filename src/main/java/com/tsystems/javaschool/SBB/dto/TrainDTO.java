package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO {

    private int id;
    private String trainName;
    private int capacity;

    public String toString(){
        return "TrainDTO object name: " + trainName ;
    }
}

package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfoDTO {
    int trainId;
    List<String> stations;
    List<String> times;
}

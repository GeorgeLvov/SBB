package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class StationValidator implements Validator {

    @Autowired
    private StationService stationService;

    @Override
    public boolean supports(Class<?> aClass) {
        return StationDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        StationDTO stationDTO = (StationDTO) o;

        if (stationService.findStationDTOByTitle(stationDTO.getTitle()) != null) {
            errors.rejectValue("title", "Duplicate.stationTitle");
        }

    }
}


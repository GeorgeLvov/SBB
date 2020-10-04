package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;


@Component
public class StationValidator implements Validator {

    @Autowired
    StationService stationService;

    @Override
    public boolean supports(Class<?> aClass) {
        return StationDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        StationDTO stationDTO = (StationDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Required.stationTitle");

        if (stationDTO.getTitle().length() < 3 || stationDTO.getTitle().length() > 50) {
            errors.rejectValue("title", "Size.stationTitle");
        }

        if (stationService.findByStationDTOTitle(stationDTO.getTitle()) != null) {
            errors.rejectValue("title", "Duplicate.stationTitle");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z \\-]+$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(stationDTO.getTitle()).matches())) {
            errors.rejectValue("title", "Invalid.stationTitle");
        }

    }
}


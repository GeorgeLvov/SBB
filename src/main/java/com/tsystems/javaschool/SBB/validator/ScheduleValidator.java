package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.ScheduleInfoDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ScheduleValidator implements Validator {


    private ScheduleInfoDTO scheduleInfoDTO;

    public void setScheduleInfoDTO(ScheduleInfoDTO scheduleInfoDTO) {
        this.scheduleInfoDTO = scheduleInfoDTO;
    }




    public ScheduleValidator(ScheduleInfoDTO scheduleInfoDTO) {
        this.scheduleInfoDTO = scheduleInfoDTO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ScheduleInfoDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (scheduleInfoDTO.getStations().contains("")) {
                errors.rejectValue("stations", "Size.userForm.username");

        }
    }


}

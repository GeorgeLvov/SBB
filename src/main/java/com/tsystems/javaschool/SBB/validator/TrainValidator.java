package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class TrainValidator implements Validator {

    @Autowired
    private TrainService trainService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TrainDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        TrainDTO trainDTO = (TrainDTO) o;

        if (trainService.findTrainByName(trainDTO.getTrainName()) != null) {
            errors.rejectValue("trainName", "Duplicate.trainName");
        }
    }
}

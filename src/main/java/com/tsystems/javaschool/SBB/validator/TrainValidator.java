package com.tsystems.javaschool.SBB.validator;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class TrainValidator implements Validator {
    
    @Autowired
    TrainService trainService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TrainDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        TrainDTO trainDTO = (TrainDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "trainName", "Required.trainName");

        if (trainDTO.getTrainName().length() < 3 || trainDTO.getTrainName().length() > 20) {
            errors.rejectValue("trainName", "Size.trainName");
        }

        if (trainService.findTrainByName(trainDTO.getTrainName()) != null) {
            errors.rejectValue("trainName", "Duplicate.trainName");
        }

        if(trainDTO.getCapacity() < 1){
            errors.rejectValue("capacity", "Wrong.capacity");
        }

        if(trainDTO.getCapacity() > 1000){
            errors.rejectValue("capacity", "Wrong.capacity2");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\-]+$", Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(trainDTO.getTrainName()).matches())) {
            errors.rejectValue("trainName", "Invalid.trainName");
        }

    }
}

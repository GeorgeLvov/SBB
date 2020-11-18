package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainValidatorTest {

    @InjectMocks
    private TrainValidator trainValidator;

    @Mock
    private TrainService trainService;

    private Validator validator;

    private static TrainDTO trainDTO;
    private static Errors errors;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        trainDTO = TrainDTO.builder()
                .id(1)
                .trainName("e-200")
                .capacity(12)
                .build();
        errors = new BeanPropertyBindingResult(trainDTO, "stationDTO");
    }

    @Test
    public void invalidTrainName() {
        trainDTO.setTrainName("e-200/?");
        Set<ConstraintViolation<TrainDTO>> violations = validator.validate(trainDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void validateTrainNoDuplicate() {
        when(trainService.findTrainByName(anyString())).thenReturn(null);
        trainValidator.validate(trainDTO, errors);
        assertFalse(errors.hasErrors());
        assertNull(errors.getFieldError("trainName"));
    }

    @Test
    public void validateTrainDuplicate() {
        when(trainService.findTrainByName(anyString())).thenReturn(new TrainDTO());
        trainValidator.validate(trainDTO, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("trainName"));
    }
}
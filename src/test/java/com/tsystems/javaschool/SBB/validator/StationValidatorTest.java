package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
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
public class StationValidatorTest {

    @InjectMocks
    private StationValidator stationValidator;
    @Mock
    private StationService stationService;

    private Validator validator;

    private static StationDTO stationDTO;
    private static Errors errors;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        stationDTO = StationDTO.builder()
                .id(1)
                .title("Bern")
                .build();
        errors = new BeanPropertyBindingResult(stationDTO, "stationDTO");
    }


    @Test
    public void invalidTrainName() {
        stationDTO.setTitle("Basel/?Hbf");
        Set<ConstraintViolation<StationDTO>> violations = validator.validate(stationDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void validateStationNameNoDuplicate() {
        when(stationService.findStationDTOByTitle(anyString())).thenReturn(null);
        stationValidator.validate(stationDTO, errors);
        assertFalse(errors.hasErrors());
        assertNull(errors.getFieldError("title"));
    }

    @Test
    public void validateStationNameDuplicate() {
        when(stationService.findStationDTOByTitle(anyString())).thenReturn(new StationDTO());
        stationValidator.validate(stationDTO, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("title"));
    }
}
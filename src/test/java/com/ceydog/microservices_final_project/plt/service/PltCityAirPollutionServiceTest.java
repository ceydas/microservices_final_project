package com.ceydog.microservices_final_project.plt.service;

import com.ceydog.microservices_final_project.plt.dto.PltCityAirPollutionSaveDto;
import com.ceydog.microservices_final_project.plt.entity.PltCityAirPollution;
import com.ceydog.microservices_final_project.plt.enums.EnumPltCategory;
import com.ceydog.microservices_final_project.plt.service.entityservice.PltCityAirPollutionEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PltCityAirPollutionServiceTest {

    @Mock
    PltCityAirPollutionEntityService pollutionEntityService;

    @Mock
    PltCityAirPollutionService pollutionService;

    @Test
    void shouldSave() {
        Date t = new Date();
        PltCityAirPollution pltCityAirPollution = mock(PltCityAirPollution.class);
        when(pltCityAirPollution.getCoCategory()).thenReturn(EnumPltCategory.POOR);
        when(pltCityAirPollution.getCityName()).thenReturn("Ankara");
        when(pltCityAirPollution.getDate()).thenReturn(t);

        when(pollutionEntityService.save(any())).thenReturn(pltCityAirPollution);
        PltCityAirPollution result = pollutionEntityService.save(pltCityAirPollution);

        assertEquals("Ankara", result.getCityName());
        assertEquals(EnumPltCategory.POOR, result.getCoCategory());
        assertEquals(t, result.getDate());
    }

    @Test
    void shouldNotSaveWhenParameterIsNull(){
    }

    @Test
    void shouldFindByCityNameAndDateBetween() {

    }

    @Test
    void findByCityName() {
    }

    @Test
    void findByCityNameAndDate() {
    }

    @Test
    void testFindByCityNameAndDate() {
    }

    @Test
    void testFindByCityNameAndDate1() {
    }

    @Test
    void deleteByCityName() {
    }

    @Test
    void deleteByCityNameAndDateBetween() {
    }

    @Test
    void deletePltCityAirPollutionByCityNameAndDate() {
    }

    @Test
    void existsByCityName() {
    }

    @Test
    void existsByCityNameAndDate() {
    }

    @Test
    void testExistsByCityNameAndDate() {
    }

    @Test
    void getCityCoordinates() {
    }

    @Test
    void getConcentrationLevelsForDay() {
    }

    @Test
    void validateCity() {
    }
}
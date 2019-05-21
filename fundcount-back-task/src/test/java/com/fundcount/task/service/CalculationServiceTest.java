package com.fundcount.task.service;

import com.fundcount.task.model.RateDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// todo add test cases
@RunWith(MockitoJUnitRunner.class)
public class CalculationServiceTest {

    @InjectMocks
    private CalculationService calculationService;

    @Mock
    private FixerConnectionService fixerConnectionService;

    @Test
    public void shouldBeDeclared() {

    }

    private RateDto getDate() {
        RateDto rate = new RateDto();
        rate.setBase("EUR");
        rate.setDate(new Date(1400250802000L));
        Map<String, Double> map = new HashMap<>();
        map.put("USD", 1.120297);
        map.put("RUB", 72.3888);
        rate.setRates(map);
        return rate;
    }


}

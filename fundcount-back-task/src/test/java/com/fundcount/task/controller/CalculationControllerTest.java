package com.fundcount.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundcount.task.service.CalculationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CalculationController.class)
public class CalculationControllerTest {
    private static final String BASE_PATH = "/api/calculation";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CalculationService calculationService;

    @Test
    public void shouldBecalledCorrectlyTest() throws Exception {
        given(calculationService.calculateProfitOrLoss(any(Date.class), any(Double.class)))
                .willReturn(0D);
        mvc.perform(get(BASE_PATH + "?amount=100&date=2014-12-12")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnZeroWithInvalidDateTest() throws Exception {
        given(calculationService.calculateProfitOrLoss(any(Date.class), any(Double.class)))
                .willReturn(0D);
        mvc.perform(get(BASE_PATH + "?amount=100&date=1111-11-11")).andExpect(status().isOk())
                .andExpect(content().json("0.0"));
    }

    @Test
    public void shouldReturnZeroWithInvalidAmountTest() throws Exception {
        given(calculationService.calculateProfitOrLoss(any(Date.class), any(Double.class)))
                .willReturn(0D);
        mvc.perform(get(BASE_PATH + "?amount=-100&date=2014-12-12")).andExpect(status().isOk())
                .andExpect(content().json("0.0"));
    }
}

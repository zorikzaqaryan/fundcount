package com.fundcount.task.controller;

import com.fundcount.task.service.CalculationService;
import com.fundcount.task.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Calculation rest controller
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/calculation")
@Validated
public class CalculationController {
    private Logger logger = LoggerFactory.getLogger(CalculationController.class);

    @Autowired
    CalculationService calculationService;

    @GetMapping
    public Double calculateProfitOrLoss(@Valid @NotNull @RequestParam("amount") Double amount,
                                        @Valid @NotNull @RequestParam
                                        @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT) Date date) {
        if (!DateUtil.isValidDateForFixer(date)) {
            logger.warn("Request with wrong date parameter");
            return 0D;
        }
        if (amount <= 0) {
            logger.warn("Amount must be greater then zero");
            return 0D;
        }
        return calculationService.calculateProfitOrLoss(date, amount);
    }


}

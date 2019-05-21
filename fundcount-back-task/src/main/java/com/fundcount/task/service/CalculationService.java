package com.fundcount.task.service;

import com.fundcount.task.model.CurrencyEnum;
import com.fundcount.task.model.RateDto;
import com.fundcount.task.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Calculation Service
 */
@Service
public class CalculationService {
    private static final String LATEST = "latest";
    private static final double FIXED_PERCENT = 0.5;

    @Autowired
    FixerConnectionService rateConnectionService;

    public double calculateProfitOrLoss(Date date, double amount) {
        //Get rates from server for specified and Current Date
        RateDto specifiedRate = rateConnectionService.
                getFixerDataByDateAndCurrency(DateUtil.getFormattedDate(date, DateUtil.DEFAULT_DATE_FORMAT));
        RateDto currentRate = rateConnectionService.getFixerDataByDateAndCurrency(LATEST);
        // Convert specified and current rates from USD to RUB
        BigDecimal withSpecifiedDate = convertBetweenCurrencies(
                currentRate.getRates().get(CurrencyEnum.USD.name()),
                currentRate.getRates().get(CurrencyEnum.RUB.name()),
                amount);
        BigDecimal withCurrentDate = convertBetweenCurrencies(
                specifiedRate.getRates().get(CurrencyEnum.USD.name()),
                specifiedRate.getRates().get(CurrencyEnum.RUB.name()),
                amount);
        BigDecimal sumDiff = calculateDifferenceBetweenTwoSums(withSpecifiedDate, withCurrentDate);
        // calculate final profit or loss
        return sumDiff.subtract(calculatePercentageOfSumByFixedPercent(sumDiff)).doubleValue();
    }


    /**
     * @param from       rate calculation with base
     * @param to         rate calculation with base
     * @param fromAmount of money
     * @return converted amount calculated from to
     */
    private BigDecimal convertBetweenCurrencies(double from, double to, double fromAmount) {
        BigDecimal convertFromToBase = BigDecimal.valueOf(fromAmount)
                .divide(BigDecimal.valueOf(from), BigDecimal.ROUND_HALF_EVEN);
        return BigDecimal.valueOf(to).multiply(convertFromToBase);
    }

    /**
     * @param sum1 from which we will subtract
     * @param sum2 which will be subtracted
     * @return difference between two sum
     */
    private BigDecimal calculateDifferenceBetweenTwoSums(BigDecimal sum1, BigDecimal sum2) {
        return sum1.subtract(sum2);
    }

    /**
     * @param sum which percentage will be calculated
     * @return percentage value
     */
    private BigDecimal calculatePercentageOfSumByFixedPercent(BigDecimal sum) {
        return sum.multiply(BigDecimal.valueOf(FIXED_PERCENT).
                divide(BigDecimal.valueOf(100), BigDecimal.ROUND_HALF_EVEN));
    }

}

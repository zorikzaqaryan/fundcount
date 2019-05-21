package com.fundcount.task.model;

import java.util.Date;
import java.util.Map;

/**
 * class for firex dto
 */
public class RateDto {
    /**
     * request status
     */
    private String success;
    /**
     * current time in milliseconds
     */
    private long timestamp;
    /**
     * base currency. For free version it's EUR
     */
    private String base;
    /**
     * specified dates for dates
     */
    private Date date;
    /**
     * rates map
     */
    private Map<String, Double> rates;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}

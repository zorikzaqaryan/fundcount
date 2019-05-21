package com.fundcount.task.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class for different date operations
 */
public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    private DateUtil() {
    }

    /**
     * @param date which will be validated
     * @return true in case of valid date
     */
    public static boolean isValidDateForFixer(Date date) {
        if (date == null) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // Firex started historical amount counting from 1999.01.01
        if (1999 > cal.get(Calendar.YEAR)) {
            return false;
        }
        return cal.getTimeInMillis() <= System.currentTimeMillis();
    }

    /**
     * @param date       which will be converted
     * @param dateFormat date will be converted to the specified format
     * @return converted date
     */
    public static String getFormattedDate(Date date, String dateFormat) {
        if (dateFormat == null || dateFormat.isEmpty()) {
            dateFormat = DEFAULT_DATE_FORMAT;
        }
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }
}
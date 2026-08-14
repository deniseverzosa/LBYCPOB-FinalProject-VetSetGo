package com.petpulse.utils;

import java.time.LocalDateTime;

public class DateTimeUtil {
    private static final int CLINIC_OPEN_HOUR = 8;
    private static final int CLINIC_CLOSE_HOUR = 18;

    // Utility method to validate appointment clinic hours
    public static boolean isWithinClinicHours(LocalDateTime timeSlot) {
        if (timeSlot == null) return false;
        int hour = timeSlot.getHour();
        return hour >= CLINIC_OPEN_HOUR && hour < CLINIC_CLOSE_HOUR;
    }
}

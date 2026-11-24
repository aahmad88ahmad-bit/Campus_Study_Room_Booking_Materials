package edu.campus;

import java.time.LocalTime;

public class BookingRules {

    private static final LocalTime OPENING_TIME = LocalTime.of(9, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(18, 0);

    public static boolean isValidDuration(int minutes) {
        return minutes >= 30 && minutes <= 120;
    }

    public static boolean isValidGroupSize(int students, int capacity) {
        return students >= 1 && students <= capacity;
    }

    public static boolean isWithinOpeningHours(LocalTime start, LocalTime end) {
        return start != null && end != null
                && start.isBefore(end)
                && !start.isBefore(OPENING_TIME)
                && !end.isAfter(CLOSING_TIME);
    }
}
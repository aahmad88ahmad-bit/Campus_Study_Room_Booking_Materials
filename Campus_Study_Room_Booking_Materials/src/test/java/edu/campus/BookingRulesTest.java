package edu.campus;

import java.time.LocalTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingRulesTest {

    @ParameterizedTest
    @CsvSource({
        "29, false",
        "30, true",
        "31, true",
        "119, true",
        "120, true",
        "121, false"
    })
    void checksDurationBoundaries(int minutes, boolean expected) {
        assertEquals(expected, BookingRules.isValidDuration(minutes));
    }

    @ParameterizedTest
    @CsvSource({
        "0, 4, false",
        "1, 4, true",
        "2, 4, true",
        "3, 4, true",
        "4, 4, true",
        "5, 4, false"
    })
    void checksGroupSizeBoundaries(int students, int capacity, boolean expected) {
        assertEquals(expected, BookingRules.isValidGroupSize(students, capacity));
    }

    @ParameterizedTest
    @CsvSource({
        "08:59, 10:00, false",
        "09:00, 10:00, true",
        "09:01, 10:00, true",
        "17:00, 17:59, true",
        "17:00, 18:00, true",
        "17:00, 18:01, false",
        "12:00, 12:00, false",
        "13:00, 12:00, false"
    })
    void checksOpeningHours(LocalTime start, LocalTime end, boolean expected) {
        assertEquals(expected, BookingRules.isWithinOpeningHours(start, end));
    }
}
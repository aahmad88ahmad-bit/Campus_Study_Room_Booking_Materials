package edu.campus;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingTest {

    @ParameterizedTest
    @CsvSource({
        "A101, 2026-12-01, 10:59, 11:30, true",
        "A101, 2026-12-01, 11:00, 11:30, false",
        "A101, 2026-12-01, 09:30, 10:00, false",
        "A101, 2026-12-01, 10:15, 10:45, true",
        "A101, 2026-12-01, 09:30, 11:30, true",
        "A101, 2026-12-01, 10:00, 11:00, true",
        "B202, 2026-12-01, 10:00, 11:00, false",
        "A101, 2026-12-02, 10:00, 11:00, false"
    })
    void detectsBookingConflicts(String roomId, LocalDate date,
                                LocalTime start, LocalTime end,
                                boolean expectedConflict) {
        Booking existing = new Booking("A101", LocalDate.of(2026, 12, 1),
                LocalTime.of(10, 0), LocalTime.of(11, 0), 2);

        Booking requested = new Booking(roomId, date, start, end, 2);

        assertEquals(expectedConflict, existing.conflictsWith(requested));
    }
}
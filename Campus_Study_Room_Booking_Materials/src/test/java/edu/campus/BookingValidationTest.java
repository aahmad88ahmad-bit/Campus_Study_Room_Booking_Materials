package edu.campus;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingValidationTest {

    @ParameterizedTest
    @CsvSource({
        "X999, 10:00, 11:00, 2, Unknown room.",
        "A101, 10:00, 10:29, 2, Booking duration must be between 30 and 120 minutes.",
        "A101, 10:00, 11:00, 0, Group size must be between 1 and 4.",
        "A101, 10:00:30, 11:00, 2, Enter start and end times in whole minutes."
    })
    void rejectsInvalidBookingWithReason(String roomId, LocalTime start,
                                         LocalTime end, int students,
                                         String expectedMessage) {
        BookingService service = new BookingService();
        Booking request = new Booking(roomId, LocalDate.of(2026, 12, 1),
                start, end, students);

        assertEquals(expectedMessage, service.book(request));
    }
}
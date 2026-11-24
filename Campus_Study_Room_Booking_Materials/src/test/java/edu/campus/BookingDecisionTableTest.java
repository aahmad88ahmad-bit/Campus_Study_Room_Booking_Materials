package edu.campus;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingDecisionTableTest {

    @ParameterizedTest
    @CsvSource({
        "4, 11:00, 11:30, Booking accepted.",
        "4, 10:30, 11:00, This room is already booked at that time.",
        "5, 11:00, 11:30, Group size must be between 1 and 4.",
        "5, 10:30, 11:00, Group size must be between 1 and 4."
    })
    void checksCapacityAndConflict(int students, LocalTime start,
                                   LocalTime end, String expected) {
        BookingService service = new BookingService();
        LocalDate date = LocalDate.of(2026, 12, 1);

        Booking existing = new Booking("A101", date,
                LocalTime.of(10, 0), LocalTime.of(11, 0), 2);
        assertEquals("Booking accepted.", service.book(existing));

        Booking request = new Booking("A101", date, start, end, students);
        assertEquals(expected, service.book(request));
    }
}
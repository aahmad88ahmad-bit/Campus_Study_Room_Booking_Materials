package edu.campus;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingServiceRejectionTest {

    private final BookingService service = new BookingService();
    private final LocalDate date = LocalDate.of(2026, 12, 1);
    private final LocalTime start = LocalTime.of(10, 0);
    private final LocalTime end = LocalTime.of(11, 0);

    @Test
    void rejectsMissingRequest() {
        assertEquals("Room and date are required.", service.book(null));
    }

    @Test
    void rejectsMissingRoomId() {
        Booking request = new Booking(null, date, start, end, 2);
        assertEquals("Room and date are required.", service.book(request));
    }

    @Test
    void rejectsMissingDate() {
        Booking request = new Booking("A101", null, start, end, 2);
        assertEquals("Room and date are required.", service.book(request));
    }

    @Test
    void rejectsBookingStartingBeforeOpening() {
        Booking request = new Booking("A101", date,
                LocalTime.of(8, 59), LocalTime.of(9, 59), 2);

        assertEquals("Enter a time between 09:00 and 18:00 with the end after the start.",
                service.book(request));
    }

    @Test
    void rejectsEndTimeWithSeconds() {
        Booking request = new Booking("A101", date, start,
                LocalTime.of(11, 0, 30), 2);

        assertEquals("Enter start and end times in whole minutes.",
                service.book(request));
    }
}
package edu.campus;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingServiceTest {

    private final BookingService service = new BookingService();
    private final LocalDate date = LocalDate.of(2026, 12, 1);
    private final LocalTime start = LocalTime.of(10, 0);
    private final LocalTime end = LocalTime.of(11, 0);

    @Test
    void acceptedBookingBlocksTheSameSlot() {
        Booking first = new Booking("A101", date, start, end, 2);
        Booking second = new Booking("A101", date, start, end, 3);

        assertEquals("Booking accepted.", service.book(first));
        assertEquals("This room is already booked at that time.", service.book(second));
    }

    @Test
    void rejectedBookingLeavesTheSlotAvailable() {
        Booking tooLarge = new Booking("A101", date, start, end, 5);
        Booking fits = new Booking("A101", date, start, end, 4);

        assertEquals("Group size must be between 1 and 4.", service.book(tooLarge));
        assertEquals("Booking accepted.", service.book(fits));
    }
}
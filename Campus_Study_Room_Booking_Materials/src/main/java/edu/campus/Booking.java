package edu.campus;

import java.time.LocalDate;
import java.time.LocalTime;

public record Booking(String roomId, LocalDate date, LocalTime start,
                      LocalTime end, int groupSize) {

    public boolean conflictsWith(Booking other) {
        return roomId.equals(other.roomId)
                && date.equals(other.date)
                && start.isBefore(other.end)
                && other.start.isBefore(end);
    }
}
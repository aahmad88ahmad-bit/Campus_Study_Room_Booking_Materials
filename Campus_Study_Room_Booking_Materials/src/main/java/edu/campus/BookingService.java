package edu.campus;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingService {

    private final Map<String, Integer> roomCapacities = Map.of(
            "A101", 4,
            "B202", 8
    );

    private final List<Booking> bookings = new ArrayList<>();

    public String book(Booking request) {
        if (request == null || request.roomId() == null || request.date() == null) {
            return "Room and date are required.";
        }

        Integer capacity = roomCapacities.get(request.roomId());
        if (capacity == null) {
            return "Unknown room.";
        }

        if (!BookingRules.isWithinOpeningHours(request.start(), request.end())) {
            return "Enter a time between 09:00 and 18:00 with the end after the start.";
        }

        if (!request.start().equals(request.start().withSecond(0).withNano(0))
                || !request.end().equals(request.end().withSecond(0).withNano(0))) {
            return "Enter start and end times in whole minutes.";
        }

        int minutes = (int) Duration.between(request.start(), request.end()).toMinutes();
        if (!BookingRules.isValidDuration(minutes)) {
            return "Booking duration must be between 30 and 120 minutes.";
        }

        if (!BookingRules.isValidGroupSize(request.groupSize(), capacity)) {
            return "Group size must be between 1 and " + capacity + ".";
        }

        for (Booking existing : bookings) {
            if (existing.conflictsWith(request)) {
                return "This room is already booked at that time.";
            }
        }

        bookings.add(request);
        return "Booking accepted.";
    }
}
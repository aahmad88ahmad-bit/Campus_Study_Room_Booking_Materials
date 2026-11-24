package edu.campus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BookingService service = new BookingService();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Campus Study Room Booking");
            System.out.println("Rooms: A101 (4 students), B202 (8 students)");

            while (true) {
                System.out.print("\nRoom ID (or exit): ");
                String roomId = scanner.nextLine().trim();

                if (roomId.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    System.out.print("Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine().trim());

                    System.out.print("Start time (HH:mm): ");
                    LocalTime start = LocalTime.parse(scanner.nextLine().trim());

                    System.out.print("End time (HH:mm): ");
                    LocalTime end = LocalTime.parse(scanner.nextLine().trim());

                    System.out.print("Number of students: ");
                    int groupSize = Integer.parseInt(scanner.nextLine().trim());

                    Booking request = new Booking(
                            roomId.toUpperCase(Locale.ROOT), date, start, end, groupSize
                    );
                    System.out.println(service.book(request));

                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date or time. Example: 2026-12-01, 10:30.");
                } catch (NumberFormatException e) {
                    System.out.println("Enter a whole number for the group size.");
                }
            }
        }
    }
}
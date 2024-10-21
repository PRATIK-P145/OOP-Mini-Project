import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private static final String BOOKINGS_FILE = "bookings.txt";
    
    public static void saveBooking(String name, Flight flight) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE, true))) {
            writer.write(name + ";" + flight.getFlightNumber() + ";" + flight.getOrigin() + ";" + flight.getDestination() + ";" + flight.getDepartureTime());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllBookings() {
        List<String> bookings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKINGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bookings.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public static void cancelBooking(String flightNumber) {
        List<String> bookings = getAllBookings();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE))) {
            for (String booking : bookings) {
                if (!booking.contains(flightNumber)) {
                    writer.write(booking);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


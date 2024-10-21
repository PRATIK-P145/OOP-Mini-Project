import java.io.Serializable;
public class Flight implements Serializable {
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;

    public Flight(String flightNumber, String origin, String destination, String departureTime) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    @Override
    public String toString() {
        return "Flight No: " + flightNumber + ", From: " + origin + ", To: " + destination + ", Time: " + departureTime;
    }
}
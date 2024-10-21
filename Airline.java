import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class Airline extends JFrame {
    private JTextField nameField, flightNumberField;
    private JTextArea bookingDisplayArea;

    private Flight[] availableFlights = {
        new Flight("AI101", "New York", "London", "10:00 AM"),
        new Flight("AI102", "Mumbai", "Paris", "02:00 PM"),
        new Flight("AI103", "Tokyo", "Sydney", "05:30 PM")
    };

    public Airline() {
        setTitle("Airline Management System");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        // Booking Panel
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(new GridLayout(3, 2));

        bookingPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        bookingPanel.add(nameField);

        bookingPanel.add(new JLabel("Flight Number:"));
        flightNumberField = new JTextField();
        bookingPanel.add(flightNumberField);

        JButton bookButton = new JButton("Book Flight");
        bookingPanel.add(bookButton);
        JButton cancelButton = new JButton("Cancel Flight");
        bookingPanel.add(cancelButton);

        add(bookingPanel);

        // Available Flights Panel
        JPanel flightPanel = new JPanel();
        flightPanel.setLayout(new BorderLayout());
        flightPanel.add(new JLabel("Available Flights:"), BorderLayout.NORTH);

        JTextArea flightListArea = new JTextArea();
        for (Flight flight : availableFlights) {
            flightListArea.append(flight.toString() + "\n");
        }
        flightListArea.setEditable(false);
        flightPanel.add(new JScrollPane(flightListArea), BorderLayout.CENTER);

        add(flightPanel);

        // Booking Display Panel
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());

        bookingDisplayArea = new JTextArea();
        bookingDisplayArea.setEditable(false);
        displayPanel.add(new JScrollPane(bookingDisplayArea), BorderLayout.CENTER);

        add(displayPanel);

        // Button Actions
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String flightNumber = flightNumberField.getText();
                Flight flight = getFlightByNumber(flightNumber);
                if (flight != null) {
                    Booking.saveBooking(name, flight);
                    refreshBookingDisplay();
                    JOptionPane.showMessageDialog(null, "Booking Successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Flight Number");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightNumber = flightNumberField.getText();
                Booking.cancelBooking(flightNumber);
                refreshBookingDisplay();
                JOptionPane.showMessageDialog(null, "Booking Cancelled!");
            }
        });

        refreshBookingDisplay();
    }

    private Flight getFlightByNumber(String flightNumber) {
        for (Flight flight : availableFlights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    private void refreshBookingDisplay() {
        bookingDisplayArea.setText("");
        List<String> bookings = Booking.getAllBookings();
        for (String booking : bookings) {
            bookingDisplayArea.append(booking + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Airline().setVisible(true);
            }
        });
    }
}
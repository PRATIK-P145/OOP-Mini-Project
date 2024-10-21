import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightSearchApp {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Search Flight Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create the panel for input fields and button
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add input fields and labels
        panel.add(new JLabel("Source:"));
        JComboBox<String> sourceComboBox = new JComboBox<>(new String[]{"Mumbai", "Delhi", "Bangalore"});
        panel.add(sourceComboBox);

        panel.add(new JLabel("Destination:"));
        JComboBox<String> destinationComboBox = new JComboBox<>(new String[]{"Mumbai", "Delhi", "Bangalore"});
        panel.add(destinationComboBox);

        panel.add(new JLabel("Class:"));
        JComboBox<String> classComboBox = new JComboBox<>(new String[]{"Business", "Economy", "First Class"});
        panel.add(classComboBox);

        // Add search button
        JButton searchButton = new JButton("Search");
        panel.add(searchButton);

        // Add search all button
        JButton searchAllButton = new JButton("Search All");
        panel.add(searchAllButton);

        frame.add(panel, BorderLayout.NORTH);

        // Create the table for displaying flight details
        String[] columnNames = {"Flight_Id", "Flight_Name", "Source", "Destination", "Date", "Arrival_Time", "Departure_Time", "Flight_Price"};
        DefaultTableModel modelTable = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(modelTable);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);

        // Add status bar
        JLabel statusBar = new JLabel("Ready");
        frame.add(statusBar, BorderLayout.SOUTH);

        // Sample data for flights
        Object[][] flightData = {
            {"1", "Air India", "Mumbai", "Delhi", "2024-10-21", "10:00", "12:00", "5000"},
            {"2", "IndiGo", "Delhi", "Mumbai", "2024-10-22", "14:00", "16:00", "4500"},
            {"3", "SpiceJet", "Bangalore", "Delhi", "2024-10-23", "09:00", "11:30", "5500"},
            {"4", "Vistara", "Mumbai", "Bangalore", "2024-10-24", "18:00", "20:00", "6000"},
            {"5", "GoAir", "Delhi", "Bangalore", "2024-10-25", "07:00", "09:30", "4800"},
            {"6", "AirAsia", "Bangalore", "Mumbai", "2024-10-26", "13:00", "15:00", "5300"},
            {"7", "Air India", "Mumbai", "Delhi", "2024-10-27", "11:00", "13:00", "5200"},
            {"8", "IndiGo", "Delhi", "Mumbai", "2024-10-28", "15:00", "17:00", "4700"},
            {"9", "SpiceJet", "Bangalore", "Delhi", "2024-10-29", "08:00", "10:30", "5600"},
            {"10", "Vistara", "Mumbai", "Bangalore", "2024-10-30", "19:00", "21:00", "6100"}
        };

        // Action listener for Search All button
        searchAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelTable.setRowCount(0); // Clear existing rows
                for (Object[] flight : flightData) {
                    modelTable.addRow(flight);
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }
}

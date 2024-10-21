import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

class LoginRegisterApp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private JLabel messageLabel;

    private static final String USERS_FILE = "users.txt";

    // Constructor to set up GUI components
    public LoginRegisterApp() {
        setTitle("Login/Register System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(0,31,63));
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        // Title Label
        JLabel titleLabel = new JLabel("Login or Register", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);

        // Username Input
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Username: "));
        usernameField = new JTextField(20);
        usernamePanel.add(usernameField);
        add(usernamePanel);

        // Password Input
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Password: "));
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        // Message Label
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        add(messageLabel);

        // Buttons
        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel);

        // Button Actions
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
    }

    // Login logic: checks if username and password match
    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        Map<String, String> users = loadUsersFromFile();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            messageLabel.setForeground(Color.GREEN);
            messageLabel.setText("Login successful! Welcome " + username + ".");
        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Invalid username or password.");
        }
    }

    // Register logic: adds new username and password to the file
    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        Map<String, String> users = loadUsersFromFile();

        if (users.containsKey(username)) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Username already exists. Choose a different username.");
        } else {
            saveUserToFile(username, password);
            messageLabel.setForeground(Color.GREEN);
            messageLabel.setText("Registration successful! Please login.");
        }
    }

    // Load users from the file into a HashMap
    private Map<String, String> loadUsersFromFile() {
        Map<String, String> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Save a new user to the file
    private void saveUserToFile(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + ";" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginRegisterApp().setVisible(true);
            }
        });
    }
}

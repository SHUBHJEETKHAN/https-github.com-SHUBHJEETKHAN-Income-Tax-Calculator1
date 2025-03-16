import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomeTaxCalculatorGUI {
    private static JFrame frame;
    private static JTextField usernameField, incomeField;
    private static JPasswordField passwordField;
    private static JLabel loginMessage, taxResult;

    public static void main(String[] args) {
        showLoginScreen();
    }

    private static void showLoginScreen() {
        frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Load Background Image
        JLabel background = new JLabel(new ImageIcon("background1.jpg"));
        background.setBounds(0, 0, 400, 300);
        frame.setContentPane(background);

        // Transparent panel for UI elements
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(50, 30, 300, 200);
        panel.setOpaque(false);
        frame.add(panel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 20, 100, 30);
        userLabel.setForeground(Color.WHITE);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 20, 100, 30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 60, 100, 30);
        passLabel.setForeground(Color.WHITE);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 60, 100, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 100, 30);
        panel.add(loginButton);

        loginMessage = new JLabel("");
        loginMessage.setBounds(50, 140, 200, 30);
        loginMessage.setForeground(Color.RED);
        panel.add(loginMessage);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (authenticate(usernameField.getText(), new String(passwordField.getPassword()))) {
                    frame.dispose();
                    showTaxCalculator();
                } else {
                    loginMessage.setText("Invalid credentials. Try again.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("password");
    }

    private static void showTaxCalculator() {
        frame = new JFrame("Income Tax Calculator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Load Background Image
        JLabel background = new JLabel(new ImageIcon("background1.jpg"));
        background.setBounds(0, 0, 400, 300);
        frame.setContentPane(background);

        // Transparent panel for UI elements
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(50, 30, 300, 200);
        panel.setOpaque(false);
        frame.add(panel);

        JLabel incomeLabel = new JLabel("Enter Income:");
        incomeLabel.setBounds(50, 20, 100, 30);
        incomeLabel.setForeground(Color.WHITE);
        panel.add(incomeLabel);

        incomeField = new JTextField();
        incomeField.setBounds(150, 20, 100, 30);
        panel.add(incomeField);

        JButton calculateButton = new JButton("Calculate Tax");
        calculateButton.setBounds(100, 60, 150, 30);
        panel.add(calculateButton);

        taxResult = new JLabel("");
        taxResult.setBounds(50, 100, 200, 30);
        taxResult.setForeground(Color.WHITE);
        panel.add(taxResult);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double income = Double.parseDouble(incomeField.getText());
                    double tax = calculateIncomeTax(income);
                    taxResult.setText("Calculated Tax: ₹" + tax);
                } catch (NumberFormatException ex) {
                    taxResult.setText("Invalid income amount!");
                }
            }
        });

        frame.setVisible(true);
    }

    private static double calculateIncomeTax(double income) {
        double taxableIncome = income - 75000;
        if (taxableIncome <= 400000) return 0;
        else if (taxableIncome <= 800000) return (taxableIncome - 400000) * 0.05;
        else if (taxableIncome <= 1200000) return (taxableIncome - 800000) * 0.10 + 20000;
        else if (taxableIncome <= 1600000) return (taxableIncome - 1200000) * 0.15 + 60000;
        else if (taxableIncome <= 2000000) return (taxableIncome - 1600000) * 0.20 + 120000;
        else if (taxableIncome <= 2400000) return (taxableIncome - 2000000) * 0.25 + 200000;
        else return (taxableIncome - 2400000) * 0.30 + 300000;
    }
}

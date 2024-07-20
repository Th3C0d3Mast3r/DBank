package BankingBased;

import java.sql.*;
import java.util.Scanner;

import static BankingBased.Main.con;

public class CheckBalance {
    static Scanner obj = new Scanner(System.in);

    public void enquireBalance() {
        System.out.print("Enter First Name: ");
        String firstName = obj.nextLine();

        System.out.print("Enter Password: ");
        String password = obj.nextLine();

        try {
            // Query the current balance
            PreparedStatement getBalanceSmt = con.prepareStatement("SELECT custBalance FROM CustomerLog WHERE firstName = ? AND custPswd = ?");
            getBalanceSmt.setString(1, firstName);
            getBalanceSmt.setString(2, password);
            ResultSet rs = getBalanceSmt.executeQuery();

            if (rs.next()) {
                String balance = rs.getString("custBalance");
                System.out.println("Your current balance is: " + balance);
            } else {
                System.out.println("No customer found with the specified credentials.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

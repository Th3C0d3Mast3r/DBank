package BankingBased;

import java.sql.*;
import java.util.Scanner;

import static BankingBased.Main.con;

public class WithdrawFunds {
    static Scanner obj = new Scanner(System.in);

    public void withdrawMoney() {
        System.out.print("Enter First Name: ");
        String firstNames = obj.nextLine();

        System.out.print("Enter Password: ");
        String password = obj.nextLine();

        System.out.println("How Much Money Do You Wish to Withdraw?");
        String amtStr = obj.nextLine();

        try {
            double withdrawAmount = Double.parseDouble(amtStr);

            // Retrieve current balance
            PreparedStatement getBalanceSmt = con.prepareStatement("SELECT custBalance FROM CustomerLog WHERE firstName = ? AND custPswd = ?");
            getBalanceSmt.setString(1, firstNames);
            getBalanceSmt.setString(2, password);
            ResultSet rs = getBalanceSmt.executeQuery();

            if (rs.next()) {
                double currentBalance = Double.parseDouble(rs.getString("custBalance"));
                if (currentBalance <= 0) {
                    System.out.println("Whoops! Your account has zero balance.");
                    System.out.println("Cannot withdraw funds from an empty account.");
                } else if (currentBalance < withdrawAmount) {
                    System.out.println("Whoops! You seem to have insufficient funds in your account.");
                    System.out.println("Please enter an amount less than or equal to your current balance.");
                } else {
                    double newBalance = currentBalance - withdrawAmount;

                    // Update the custBalance for the specified firstName and password
                    PreparedStatement updateBalanceSmt = con.prepareStatement("UPDATE CustomerLog SET custBalance = ? WHERE firstName = ? AND custPswd = ?");
                    updateBalanceSmt.setString(1, String.valueOf(newBalance)); // Set the new balance
                    updateBalanceSmt.setString(2, firstNames); // Specify which customer to update
                    updateBalanceSmt.setString(3, password);

                    int rowsUpdated = updateBalanceSmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Money withdrawn successfully.\nNew balance: " + newBalance);
                    } else {
                        System.out.println("Failed to update balance.");
                    }
                }
            } else {
                System.out.println("No customer found with the specified credentials.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}

package BankingBased;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DOBChecker {
    boolean dobCheck(String st) {
        // Expected date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            // Parse the input date
            LocalDate inputDate = LocalDate.parse(st, formatter);
            LocalDate today = LocalDate.now();

            // Check if the date is not in the future
            if (inputDate.isAfter(today)) {
                System.out.println("Date of birth cannot be in the future.");
                return false;
            }

            // Check year range
            int years = inputDate.getYear();
            if (years < 0 || years > today.getYear()) {
                System.out.println("Invalid year.");
                return false;
            }

            // Additional checks can be added here if needed
            return true; // Date is valid
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return false;
        }
    }
}

package BankingBased;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Main {
    static Scanner obj = new Scanner(System.in);
    static Connection con;
    static Buffers buffer = new Buffers();

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankDataBase", "root", "qwertyuiop1234");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        //this is the intro of all-here we go for REGISTRATION/LOGIN
        System.out.println("Welcome to D-Bank\n");
        System.out.println("1) LOGIN TO YOUR ACCOUNT\n2) SET-UP YOUR NEW ACCOUNT");
        String reply1 = obj.nextLine();

        if (reply1.equals("1") || reply1.equalsIgnoreCase("login") || reply1.charAt(0) == 'l') {
            loginCred();
        } else if (reply1.equals("2") || reply1.equalsIgnoreCase("set up") || reply1.equalsIgnoreCase("new") || reply1.equalsIgnoreCase("new account") || reply1.charAt(0) == 'n' || reply1.charAt(0) == 's') {
            setupCred();
        } else {
            System.out.println("Whoops! Something Went wrong");
        }
    }

    private static void setupCred() {
        System.out.println("\fFill Out the Following Details to Set Up your New Account with D-Bank\n\n");
        System.out.print("Enter First Name: ");
        String fName = obj.nextLine();

        System.out.print("Enter Middle Name: ");
        String mName = obj.nextLine();

        System.out.print("Enter Last Name: ");
        String lName = obj.nextLine();

        System.out.print("Enter Date Of Birth (YYYY-MM-DD): "); // Ensure date format is correct
        String dob = obj.nextLine();
        DOBChecker d=new DOBChecker();
        if(!d.dobCheck(dob))
        {
            System.out.println("Invalid Date of Birth. Please try again.");
            setupCred();
            return;
        }

        System.out.print("Enter Mobile Number: ");
        String mobNo = obj.nextLine();

        System.out.print("Enter Aadhar Card Number: ");
        String aadhar = obj.nextLine();

        System.out.println("Do You Confirm the Details Provided above?");
        String replyss = obj.nextLine();

        if (replyss.equalsIgnoreCase("yes") || replyss.charAt(0) == 'y' || replyss.charAt(0) == 'Y') {
            System.out.print("Set Password: ");
            String pswd1 = obj.nextLine();
            String pswd = pswd1.trim();
            System.out.print("Re-Enter Password to Confirm: ");
            String reEnter = obj.nextLine();
            if (pswd.equals(reEnter.trim())) {
                System.out.println("CONFIRMED");

                buffer.bufferFunction();

                addData(fName, mName, lName, dob, mobNo, aadhar, pswd);
                commonPath();
            }
            else {
                setupCred();
            }
        }
        else {
            System.out.println("Re-Enter the Data: \n");
            setupCred();
        }
    }

    private static void loginCred() {
        System.out.println("Welcome to D-Bank");
        System.out.print("Enter Your Full Name: ");
        String nameInput = obj.nextLine();
        System.out.print("Enter Password: ");
        String pswdInput = obj.nextLine();

        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM CustomerLog WHERE CONCAT(firstName, ' ', middleName, ' ', lastName) = ? AND custPswd = ?");
            stmt.setString(1, nameInput);
            stmt.setString(2, pswdInput);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful! Welcome " + rs.getString("firstName"));
                commonPath();
            } else {
                System.out.println("Invalid credentials. Please try again.");
                run();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void addData(String fName, String mName, String lName, String dob, String mob, String aadharNo, String password) {
        try {
            // Parse the date correctly
            Date sqlDate = Date.valueOf(dob); // Ensure the format is correct

            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO CustomerLog (firstName, middleName, lastName, dob, mobNo, aadharNo, custPswd, custBalance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, fName);
            stmt.setString(2, mName);
            stmt.setString(3, lName);
            stmt.setDate(4, sqlDate);
            stmt.setString(5, mob);
            stmt.setString(6, aadharNo);
            stmt.setString(7, password);
            stmt.setString(8, "0.00"); // Initialize balance to zero

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void commonPath() {
        System.out.println("What Action Do You Want to Have?");
        System.out.println("1) DEPOSIT FUNDS\n2) WITHDRAW FUNDS\n3) SEE BALANCE\n4) EXIT");
        String options = obj.nextLine();

        if (options.equalsIgnoreCase("deposit") || options.charAt(0) == 'd' || options.charAt(0) == 'D' || options.equals("1")) {
            DepositFunds df = new DepositFunds();
            df.depositMoney();
            buffer.bufferFunction();
            commonPath();
        } else if (options.equalsIgnoreCase("withdraw") || options.charAt(0) == 'w' || options.charAt(0) == 'W' || options.equals("2")) {
            WithdrawFunds wf = new WithdrawFunds();
            wf.withdrawMoney();
            buffer.bufferFunction();
            commonPath();
        } else if (options.equalsIgnoreCase("exit") || options.charAt(0) == 'e' || options.charAt(0) == 'E' || options.equals("4")) {
            System.out.println("Logging Out - Thank You  :)");
            System.exit(0);
        } else if (options.equalsIgnoreCase("see balance") || options.equalsIgnoreCase("balance") || options.charAt(0) == 's' || options.charAt(0) == 'b' || options.equals("3")) {
            CheckBalance cb = new CheckBalance();
            cb.enquireBalance();
            buffer.bufferFunction();
            commonPath();
        } else {
            System.out.println("Wrong Choice - Retry!");
            commonPath();
        }
    }
}
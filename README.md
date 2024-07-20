# D-BANK: Banking System

Welcome to the Banking System project, a simple and functional banking application built using Java and MySQL. This project allows users to REGISTER, LOG-IN, DEPOSIT, WITHDRAW, and CHECK their FUNDS. It demonstrates the integration of Java with a MySQL database for a seamless banking experience.

## Features

- **User Registration**: New users can create an account by providing their personal details.
- **User Login**: Existing users can log in using their credentials.
- **Deposit Funds**: Users can deposit money into their accounts.
- **Withdraw Funds**: Users can withdraw money from their accounts, ensuring they don't withdraw more than their current balance.
- **Check Balance**: Users can check their current account balance.

## Prerequisites

- Java Development Kit (JDK)
- MySQL Server
- MySQL JDBC Driver

## Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/banking-system.git
   cd banking-system
   ```

2. **Set Up the MySQL Database**

   - Create a database named `BankDataBase`.
   - Run the following SQL script to create the necessary table:

     ```sql
     CREATE TABLE CustomerLog (
         id INT AUTO_INCREMENT PRIMARY KEY,
         firstName VARCHAR(50),
         middleName VARCHAR(50),
         lastName VARCHAR(50),
         dob DATE,
         mobNo VARCHAR(15),
         aadharNo VARCHAR(20),
         custPswd VARCHAR(50),
         custBalance DECIMAL(10, 2)
     );
     ```

3. **Update Database Configuration**

   - Ensure the database connection details in the `Main` class match your MySQL setup:
     ```java
     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankDataBase", "root", "your-password");
     ```

4. **Compile and Run the Application**

   - Compile the Java files:
     ```bash
     javac -d bin src/BankingBased/*.java
     ```

   - Run the application:
     ```bash
     java -cp "bin:lib/mysql-connector-java-8.0.23.jar" BankingBased.Main
     ```

## Usage

1. **Start the Application**

   - Run the main class `Main` to start the application.

2. **Follow the Prompts**

   - You will be guided through user registration, login, and other banking operations through console prompts.

## Contributing
```I would be more than happy if there are any features or updates that would make this more seamless.```

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

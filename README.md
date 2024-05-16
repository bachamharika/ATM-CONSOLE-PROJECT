# ATM System

## Overview
This is a simple ATM system implemented in Java. It allows users to authenticate using their user ID and PIN, view their balance, withdraw funds, deposit funds, transfer money to other users, and view their transaction history. The system uses a console-based interface for interaction.

## Features
1. **User Authentication**: Users must enter a valid user ID and PIN to access the system.
2. **View Balance**: Users can check their current balance.
3. **Withdraw**: Users can withdraw money from their account, provided they have sufficient funds.
4. **Deposit**: Users can deposit money into their account.
5. **Transfer**: Users can transfer money to another user's account.
6. **Transaction History**: Users can view a history of their transactions.
7. **Exit**: Users can exit the system.

## Classes and Methods

### `ATM`
- **Fields**:
  - `users`: A `Map<String, User>` storing all users.
  - `currentUser`: The currently authenticated user.
  - `scanner`: A `Scanner` object for user input.

- **Methods**:
  - `main(String[] args)`: Initializes users and handles user authentication and menu display.
  - `initializeUsers()`: Creates initial users.
  - `authenticateUser(String userID, String pin)`: Authenticates a user based on userID and PIN.
  - `displayMenu()`: Displays the main menu and handles user choices.

### `User`
- **Fields**:
  - `userID`: The user's ID.
  - `pin`: The user's PIN.
  - `balance`: The user's current balance.
  - `transactionHistory`: A list of the user's transactions.

- **Methods**:
  - Constructor to initialize a user.
  - `getUserID()`, `getPin()`, `getBalance()`, `setBalance(double balance)`: Getters and setter for user properties.
  - `getTransactionHistory()`: Returns the user's transaction history.
  - `addTransaction(Transaction transaction)`: Adds a transaction to the user's history.

### `Transaction`
- **Fields**:
  - `type`: The type of transaction (e.g., withdrawal, deposit).
  - `amount`: The amount involved in the transaction.
  - `date`: The date of the transaction.

- **Methods**:
  - Constructor to initialize a transaction.
  - `toString()`: Overrides the `toString` method to display transaction details.

### `TransactionHistory`
- **Methods**:
  - `displayTransactionHistory(User user)`: Displays the transaction history for a user.

### `Withdraw`
- **Methods**:
  - `withdraw(User user, double amount)`: Handles the withdrawal process for a user.

### `Deposit`
- **Methods**:
  - `deposit(User user, double amount)`: Handles the deposit process for a user.

### `Transfer`
- **Methods**:
  - `transfer(User sender, User recipient, double amount)`: Handles the transfer process between users.

### `Quit`
- **Methods**:
  - `quit()`: Exits the system with a goodbye message.

## How to Use
1. **Run the Program**: Compile and run the `ATM` class.
2. **Authenticate**: Enter a valid user ID and PIN to log in.
3. **Menu Options**:
   - **View Balance**: Press 1 to view your current balance.
   - **Withdraw**: Press 2 to withdraw money. Enter the amount to withdraw.
   - **Deposit**: Press 3 to deposit money. Enter the amount to deposit.
   - **Transfer**: Press 4 to transfer money. Enter the recipient's user ID and the amount to transfer.
   - **View Transaction History**: Press 5 to view your transaction history.
   - **Quit**: Press 6 to exit the system.

## Sample Users
- **User ID**: 518122, **PIN**: 1028, **Balance**: ₹1000.0
- **User ID**: 953311, **PIN**: 0709, **Balance**: ₹500.0

## Example Usage
```shell
Welcome to the ATM!
Enter user ID: 518122
Enter PIN: 1028
Authentication successful.

Choose an option:
1. View Balance
2. Withdraw
3. Deposit
4. Transfer
5. View Transaction History
6. Quit

Enter your choice: 1
Your balance is: ₹1000.0
```

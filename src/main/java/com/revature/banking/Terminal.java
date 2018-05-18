package com.revature.banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Terminal {
	private Map<Account, List<User>> userAccounts;
	// Map<User,User> functions as a set that can easily pull out values
	// based on a string, since user equality/hash is based on user name
	private Map<User,User> users;
	private User active;	
	
	public Terminal() {
		// this is where start-up code goes.
		// so this is the read-from-file logic
		TerminalSerializer.getInstance().Read();
		userAccounts = TerminalSerializer.getInstance().getUserAccounts();
		users = TerminalSerializer.getInstance().getUsers();
		if (users == null) {
			users = new HashMap<>();
			User admin = User.GetAdmin();
			users.put(admin, admin);
		}
		if (userAccounts == null) {
			userAccounts = new HashMap<>();
		}		
	}
	
	
	public boolean LogIn(String name, String password) {
		for (User u: users.keySet()) {
			if (u.getUsername().equals(name)) {
				if (u.CheckPassword(password)) {
					setActive(u);
					return true;
				}
				break;
			}
		}
		return false;
	}
	
	public User getActive() {
		return active;
	}

	private void setActive(User active) {
		this.active = active;
	}

	public boolean NewUser(String name, String password, String confirm) {
		if (password != null && password.equals(confirm)) {
			User toAdd = new User(name, password);
			if (!users.containsKey(toAdd)) {
				users.put(toAdd, toAdd);
				List<User> userList = new ArrayList<>();
				userList.add(toAdd);
				Account newAccount = new Account();
				toAdd.addAccount(newAccount);
				userAccounts.put(newAccount, userList);
				TerminalSerializer.getInstance().WriteUsers(users);
				AccountNumberSerializer.getInstance().Write(Account.GetCurrentNextAccountNumber());
				return true;
			}
		}
		return false;
	}
	
	public boolean NewUser(String name, String password, String confirm, double balance) {
		if (password != null && password.equals(confirm)) {
			User toAdd = new User(name, password);
			if (!users.containsKey(toAdd)) {
				users.put(toAdd, toAdd);
				List<User> userList = new ArrayList<>();
				userList.add(toAdd);
				Account newAccount = new Account(balance);
				toAdd.addAccount(newAccount);
				userAccounts.put(newAccount, userList);
				TerminalSerializer.getInstance().WriteUsers(users);
				AccountNumberSerializer.getInstance().Write(Account.GetCurrentNextAccountNumber());
				return true;
			}
		}
		return false;
	}
	
	public void AddUserToAccount(long account, String username) {
		Account theAccount = null;
		for (Account a : userAccounts.keySet()) {
			if (a.getAccountNumber() == account) {
				theAccount = a;
				break;
			}
		}
		User user = users.get(new User(username, ""));
		
		userAccounts.get(theAccount).add(user);
		user.addAccount(theAccount);
		TerminalSerializer.getInstance().WriteUsers(users);
	}
	
	public String AddAccount() {
		Account theAccount = new Account();
		List<User> userList = new ArrayList<User>();
		userList.add(active);
		userAccounts.put(theAccount, userList);
		active.addAccount(theAccount);
		TerminalSerializer.getInstance().WriteUsers(users);
		AccountNumberSerializer.getInstance().Write(Account.GetCurrentNextAccountNumber());
		return Long.toString(theAccount.getAccountNumber());
	}
	
	public boolean HasMultiple() {
		if (active == null) {
			return false;
		}
		return active.AccountCount() > 1;
	}
	
	public boolean HasMultiple(String user) {
		return users.get(new User(user,"")).AccountCount() > 1;
	}
	
	public String ViewBalance() {
		if (active.AccountCount() == 1) {
			return String.format("%.2f", active.getAccount(0).getBalance());
		}
		StringBuilder accountData = new StringBuilder();
		for (int i = 0; i < active.AccountCount(); i++) {
			accountData.append(active.getAccount(i).getAccountNumber() + ": " + String.format("%.2f", active.getAccount(i).getBalance()) + "\n");
		}
		return accountData.toString();
	}
	
	public boolean Deposit(double amount) {
		if (amount < 0.0) {
			return false;
		}
		boolean success = active.getAccount(0).Deposit(amount, active.getUsername());
		TerminalSerializer.getInstance().WriteUsers(users);
		return success;
	}
	
	public boolean Withdraw(double amount) {
		if (amount < 0.0) {
			return false;
		}
		boolean success = active.getAccount(0).Withdraw(amount, active.getUsername());
		TerminalSerializer.getInstance().WriteUsers(users);
		return success;
	}
	
	public String TransactionHistory() {
		return TransactionHistory(active.getAccount(0).getAccountNumber());
	}
	
	public String TransactionHistory(long accountNumber) {
		List<Transaction> trans = active.getAccount(accountNumber).getHistory();
		StringBuilder history = new StringBuilder();
		history.append("D/W\tPrevious\tAfter\n");
		for (Transaction t : trans) {
			history.append(t.toString() + "\n");
		}
		return history.toString();
	}
	
	public String TransactionHistory(String username, long accountNumber) {
		if (!active.isAdmin()) {
			return null;
		}
		List<Transaction> trans = users.get(new User(username, "")).getAccount(accountNumber).getHistory();
		StringBuilder history = new StringBuilder();
		history.append("D/W\tPrevious\tAfter\n");
		for (Transaction t : trans) {
			history.append(t.toString() + "\n");
		}
		return history.toString();
	}
	
	public String TransactionHistory(String username) {
		if (!active.isAdmin()) {
			return null;
		}
		return TransactionHistory(username, users.get(new User(username, "")).getAccount(0).getAccountNumber());
	}
	
	public String getAccountNumbersForUser(String user, User u) {
		if (!u.isAdmin()) {
			return null;
		}
		return users.get(new User(user,"")).getAccountNumbers();
	}

	public boolean ValidAccount(long number) {
		for (Account a : userAccounts.keySet()) {
			if (a.getAccountNumber() == number) {
				return true;
			}
		}
		return false;
	}

	public boolean ValidUser(String user) {
		return users.containsKey(new User(user, ""));
	}

	public void PromoteUser(String user) {
		if (active.isAdmin()) {
			users.get(new User(user, "")).Promote();
		}
		TerminalSerializer.getInstance().WriteUsers(users);
	}


	public boolean Deposit(double amount, long accountNumber) {
		boolean success = false;
		for (int i = 0; i < active.AccountCount(); i++) {
			if (active.getAccount(i).getAccountNumber() == accountNumber) {
				if (userAccounts.get(active.getAccount(0)).size() == 1) {
					success = active.getAccount(i).Deposit(amount, active.getUsername());
				} else {
					success = active.getAccount(i).Deposit(amount, active.getUsername());
				}
				TerminalSerializer.getInstance().WriteUsers(users);
			}
		}
		return success;
	}
	
	public boolean Withdraw(double amount, long accountNumber) {
		boolean success = false;
		for (int i = 0; i < active.AccountCount(); i++) {
			if (active.getAccount(i).getAccountNumber() == accountNumber) {
				if (userAccounts.get(active.getAccount(0)).size() == 1) {
					success = active.getAccount(i).Withdraw(amount, active.getUsername());
				} else {
					success = active.getAccount(i).Withdraw(amount, active.getUsername());
				}
				TerminalSerializer.getInstance().WriteUsers(users);
			}
		}
		return success;
	}
}

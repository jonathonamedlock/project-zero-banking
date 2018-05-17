package com.revature.banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Terminal {
	private Map<Account, List<User>> userAccounts;
	private Map<User,User> users;
	private User active;	
	
	public Terminal() {
		// this is where start-up code goes.
		// so this is the read-from-file logic
		userAccounts = new HashMap<>();
		users = new HashMap<>();
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
				return true;
			}
		}
		return false;
	}
	
	/*public void AddAccount(int type) {
		
	}*/
	
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
		return Double.toString(active.getAccount(0).getBalance());
	}
	
	public boolean Deposit(double amount) {
		if (amount < 0.0) {
			return false;
		}
		return active.getAccount(0).Deposit(amount);
	}
	
	public boolean Withdraw(double amount) {
		if (amount < 0.0) {
			return false;
		}
		return active.getAccount(0).Withdraw(amount);
	}
	
	public String TransactionHistory() {
		return TransactionHistory(active.getAccount(0).getAccountNumber());
	}
	
	public String TransactionHistory(long accountNumber) {
		List<Transaction> trans = active.getAccount(accountNumber).getHistory();
		StringBuilder history = new StringBuilder();
		for (Transaction t : trans) {
			history.append(t.toString() + "\n");
		}
		return history.toString();
	}
	
	public String TransactionHistory(String username) {
		if (!active.isAdmin()) {
			return null;
		}
		return TransactionHistory(users.get(new User(username, "")).getAccount(0).getAccountNumber());
	}
	
	public String TransactionHistory(String username, long accountNumber) {
		if (!active.isAdmin()) {
			return null;
		}
		return TransactionHistory(users.get(new User(username, "")).getAccount(accountNumber).getAccountNumber());
	}
	
	public String getAccountNumbersForUser(String user, User u) {
		if (!u.isAdmin()) {
			return null;
		}
		return users.get(new User(user,"")).getAccountNumbers();
	}
}

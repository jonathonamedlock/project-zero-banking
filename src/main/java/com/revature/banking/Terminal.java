package com.revature.banking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Terminal {
	private Map<User, UserAccount> userAccounts;
	private User active;
	
	public Terminal() {
		// this is where start-up code goes.
		// so this is the read-from-file logic
		userAccounts = new HashMap<>();
	}
	
	public boolean LogIn(String name, String password) {
		for (User u: userAccounts.keySet()) {
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
			if (!userAccounts.containsKey(toAdd)) {
				userAccounts.put(toAdd, new UserAccount(toAdd, new Account()));
				return true;
			}
		}
		return false;
	}
	
	public boolean NewUser(String name, String password, String confirm, double balance) {
		if (password != null && password.equals(confirm)) {
			User toAdd = new User(name, password);
			if (!userAccounts.containsKey(toAdd)) {
				userAccounts.put(toAdd, new UserAccount(toAdd, new Account(balance)));
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
		return userAccounts.get(active).Count() > 1;
	}
	
	public String ViewBalance() {
		return userAccounts.get(active).getBalance();
	}
	
	public boolean Deposit(double amount) {
		if (amount < 0.0) {
			return false;
		}
		userAccounts.get(active).Deposit(amount);
		return true;
	}
	
	public boolean Withdraw(double amount) {
		if (amount < 0.0) {
			return false;
		}
		return userAccounts.get(active).Withdraw(amount);
	}
	
	public List<Transaction> TransactionHistory() {
		return userAccounts.get(active).getHistory();
	}
	
	public List<Transaction> TransactionHistory(String username) {
		if (!active.isAdmin()) {
			return null;
		}
		return userAccounts.get(new User(username, "")).getHistory();
	}
}

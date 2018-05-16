package com.revature.banking;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
	private List<User> users;
	private List<Account> accounts;
	
	private UserAccount() {
		accounts = new ArrayList<Account>();
		users = new ArrayList<>();
	}
	
	public UserAccount(User user, Account account) {
		this();
		accounts.add(account);
	}
	
	public void AddAccount(Account account) {
		if (!accounts.contains(account)) {
			accounts.add(account);
		}
	}
	
	public String getBalance() {
		if (accounts.size() == 1) {
			return Double.toString(accounts.get(0).getBalance());
		}
		return "";
	}
	
	public boolean Deposit(double value) {
		if (accounts.size() != 1) {
			return false;
		}
		return accounts.get(0).Deposit(value);
	}
	
	public boolean Withdraw(double value) {
		if (accounts.size() != 1) {
			return false;
		}
		return accounts.get(0).Withdraw(value);
	}

	public List<Transaction> getHistory() {
		if (accounts.size() != 1) {
			return null;
		}
		return accounts.get(0).getHistory();
	}
}

package com.revature.banking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6539420945574322431L;
	private String username;
	private String password;
	private boolean admin;
	private List<Account> accounts;
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		accounts = new ArrayList<>();
	}

	public static User GetAdmin() {
		User administrator = new User("admin", "password");
		administrator.admin = true;
		return administrator;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAdmin() {
		return admin;
	}
	
	public boolean CheckPassword(String password) {
		return this.password.equals(password);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public int AccountCount() {
		return accounts.size();
	}

	public Account getAccount(int index) {
		return accounts.get(index);
	}
	
	public Account getAccount(long accountNumber) {
		for (Account a : accounts) {
			if (accountNumber == a.getAccountNumber()) {
				return a;
			}
		}
		return null;
	}
	
	public String getAccountNumbers() {
		StringBuilder numbers = new StringBuilder();
		for (Account a : accounts) {
			numbers.append(a.getAccountNumber() + "\n");
		}
		return numbers.toString();
	}

	public void addAccount(Account newAccount) {
		accounts.add(newAccount);
	}

	public void Promote() {
		admin = true;
	}
}

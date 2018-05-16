package com.revature.banking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7115573030510489868L;
	private double balance;
	private AccountType type;
	private List<Transaction> history;
	
	public Account(double funds, AccountType type) {
		balance = funds;
		this.type = type;
		history = new ArrayList<Transaction>();
	}
	
	public Account(double funds) {
		this(funds, AccountType.CHECKING);		
	}
	
	public Account() {
		this(0.0, AccountType.CHECKING);
	}
	
	public boolean Deposit(double amount) {
		if (amount < 0) {
			return false;
		}
		history.add(new Transaction(true, balance, balance += amount));
		return true;
	}
	
	public boolean Withdraw(double amount) {
		if (amount > balance || amount < 0) {
			return false;
		}
		history.add(new Transaction(false, balance, balance -= amount));
		return true;
	}
	
	public double getBalance() {
		return balance;
	}

	public List<Transaction> getHistory() {
		return history;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((history == null) ? 0 : history.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (history == null) {
			if (other.history != null)
				return false;
		} else if (!history.equals(other.history))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
}

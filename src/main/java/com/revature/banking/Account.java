package com.revature.banking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7115573030510489868L;
	private double balance;
	private AccountType type;
	private List<Transaction> history;
	
	private static final AtomicLong NEXT_ID;
	
	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setHistory(List<Transaction> history) {
		this.history = history;
	}

	static {
		if (AccountNumberSerializer.getInstance().Read() == null) {
			NEXT_ID = new AtomicLong(0);
		} else {
			NEXT_ID = AccountNumberSerializer.getInstance().Read();
		}
	}
	
	private final long id = NEXT_ID.getAndIncrement();
		
	public long getAccountNumber() {
		return id;
	}
	
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
	
	public boolean Deposit(double amount, String by) {
		if (amount <= 0) {
			return false;
		}
		history.add(new Transaction(true, balance, balance += amount, by));
		return true;
	}
	
	public boolean Withdraw(double amount, String by) {
		if (amount > balance || amount <= 0) {
			return false;
		}
		history.add(new Transaction(false, balance, balance -= amount, by));
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
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		return true;
	}
	
	public static AtomicLong GetCurrentNextAccountNumber() {
		return NEXT_ID;
	}
}

package com.revature.banking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	static final AtomicLong NEXT_ID;
	
	static {
		long l = 0;
		// open file, read current id number from the file
		try (BufferedReader buff = new BufferedReader(new FileReader("accID.txt"))) {
			l = Long.parseLong(buff.readLine());
			buff.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} 
		NEXT_ID = new AtomicLong(l);
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
	
}

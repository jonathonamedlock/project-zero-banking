package com.revature.banking;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.banking.Account;
import com.revature.banking.AccountType;
import com.revature.banking.Transaction;

public class TestAccount {

	@Test
	public void testContruction() {
		Account t1 = new Account();
		Account t2 = new Account(0.0);
		Account t3 = new Account(0.0, AccountType.CHECKING);
		Account t4 = new Account(3.50, AccountType.CHECKING);
		Account t5 = new Account(3.50, AccountType.SAVINGS);
		
		assertNotEquals(t1, t2);
		assertNotEquals(t2, t3);
		assertNotEquals(t1, t4);
		assertNotEquals(t4, t5);
	}
	
	@Test
	public void testDeposit() {
		Account t1 = new Account();
		assertTrue(t1.Deposit(3.5, "me"));
		assertTrue(t1.Deposit(40000, "me"));
		assertFalse(t1.Deposit(-600, "me"));
		
		assertEquals(new Double(40003.5), new Double(t1.getBalance()));
	}
	
	@Test
	public void testWithdraw() {
		Account t1 = new Account(3500);
		assertTrue(t1.Withdraw(300, "me"));
		assertTrue(t1.Withdraw(3000.50, "me"));
		assertFalse(t1.Withdraw(-30000, "me"));
		
		assertEquals(new Double(199.5), new Double(t1.getBalance()));
	}
	
	@Test
	public void testTransactionHistory() {
		Account t1 = new Account(4000);
		t1.Deposit(5, "me");
		t1.Deposit(2.5, "me");
		t1.Withdraw(5.5, "me");
		t1.Deposit(6, "me");
		t1.Withdraw(1.5, "me");
		
		List<Transaction> history = new ArrayList<Transaction>();
		
		history.add(new Transaction(true, 4000, 4005, "me"));
		history.add(new Transaction(true, 4005, 4007.5, "me"));
		history.add(new Transaction(false, 4007.5, 4002, "me"));
		history.add(new Transaction(true, 4002, 4008, "me"));
		history.add(new Transaction(false, 4008, 4006.5, "me"));
		
		assertEquals(history, t1.getHistory());
	}
	
}

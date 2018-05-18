package com.revature.banking;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTerminal {

	@Test
	public void TestNewUser() {
		Terminal t = Terminal.getInstance();
		assertTrue(t.NewUser("User1", "Password1", "Password1"));
		assertFalse(t.NewUser("User1", "Password1", "Password1"));
		assertTrue(t.NewUser("User2", "Password1", "Password1", 100));
		assertFalse(t.NewUser("User3", "Password3", "Password1"));
		assertTrue(t.NewUser("User4", "Password4", "Password4", 403.2));
	}
	
	@Test
	public void TestLogIn() {
		Terminal t = Terminal.getInstance();
		t.NewUser("User1", "Password1", "Password1");
		assertNull(t.getActive());
		t.LogIn("User1", "Password1");
		assertNotNull(t.getActive());
		assertEquals("User1", t.getActive().getUsername());
		assertTrue(t.getActive().CheckPassword("Password1"));
	}

	@Test
	public void TestViewBalance() {
		Terminal t = Terminal.getInstance();
		t.NewUser("User1", "Password1", "Password1");
		t.NewUser("User2", "Password2", "Password2", 300);
		t.LogIn("User1", "Password1");
		assertEquals("0: 0.00", t.ViewBalance());
		t.LogIn("User2", "Password2");
		assertEquals("1: 300.00", t.ViewBalance());
	}
	
	@Test
	public void TestWithdraw() {
		Terminal t = Terminal.getInstance();
		t.NewUser("User1", "Password1", "Password1", 100);
		t.LogIn("User1", "Password1");
		assertTrue(t.Withdraw(1));
		assertTrue(t.Withdraw(.5));
		assertFalse(t.Withdraw(0));
		assertFalse(t.Withdraw(-1));
		assertFalse(t.Withdraw(-.5));
		assertEquals("0: 98.50", t.ViewBalance());
	}
	
	@Test
	public void TestDeposit() {
		Terminal t = Terminal.getInstance();
		t.NewUser("User1", "Password1", "Password1", 100);
		t.LogIn("User1", "Password1");
		assertTrue(t.Deposit(1));
		assertTrue(t.Deposit(.5));
		assertFalse(t.Deposit(0));
		assertFalse(t.Deposit(-1));
		assertFalse(t.Deposit(-.5));
		assertEquals("0: 101.50", t.ViewBalance());
	}
}

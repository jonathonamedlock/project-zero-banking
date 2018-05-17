package com.revature.banking;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUser {

	@Test
	public void testConstruction() {
		User t1 = new User("testUser", "testPass");
		User t2 = new User("testUser", "differentPass");
		assertTrue("testUser".equals(t1.getUsername()));
		assertTrue(t1.CheckPassword("testPass"));
		assertFalse("testUUSER".equals(t1.getUsername()));
		assertFalse(t1.CheckPassword("testPASS!"));
		assertEquals(t1, t2); // for uniqueness in username for map logic
		assertEquals(0, t1.AccountCount());
	}
	
	@Test
	public void testAccountAdding() {
		User t1 = new User("user", "pass");
		t1.addAccount(new Account());
		assertEquals(1, t1.AccountCount());
		t1.addAccount(new Account(33.2));
		assertEquals(2, t1.AccountCount());
	}
	
	@Test
	public void testAccountRetrieval() {
		User t1 = new User("user", "pass");
		Account a1 = new Account();
		Account a2 = new Account();
		t1.addAccount(a1);
		t1.addAccount(a2);
		assertEquals(a1, t1.getAccount(0));
		assertEquals(a2, t1.getAccount(1));
		assertNotEquals(a2, t1.getAccount(0));
	}
}

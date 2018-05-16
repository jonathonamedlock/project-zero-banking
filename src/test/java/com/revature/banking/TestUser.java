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
	}
}

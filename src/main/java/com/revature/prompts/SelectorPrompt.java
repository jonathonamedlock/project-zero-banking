package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class SelectorPrompt {
	private long active;
	
	private static SelectorPrompt SELECTOR = new SelectorPrompt();
	
	private SelectorPrompt() {
	}
	
	public static SelectorPrompt getInstance() {
		return SELECTOR;
	}
	
	public void SelectAccount(Terminal t, Scanner s) {
		System.out.print("User accounts\n" + t.getActive().getAccountNumbers());
		System.out.print("Enter account number: ");
		active = Long.parseLong(s.nextLine());
	}
	
	public void SelectAccount(Terminal t, Scanner s, String username) {
		String numbers = t.getAccountNumbersForUser(username, t.getActive());
		if (numbers != null) {
			System.out.print("User accounts\n" + numbers);
			System.out.print("Enter account number: ");
			active = Long.parseLong(s.nextLine());
		} else {
			System.err.println("Attempting to get account numbers from a non-admin account.");
		}
	}
	
	public long getActive() {
		return active;
	}
	
	public void clear() {
		active = -1;
	}
}

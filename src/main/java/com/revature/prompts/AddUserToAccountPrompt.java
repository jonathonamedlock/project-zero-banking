package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class AddUserToAccountPrompt implements Prompt {
	private static final AddUserToAccountPrompt ADD_USER = new AddUserToAccountPrompt();
	
	private AddUserToAccountPrompt() {
	}
	
	public static AddUserToAccountPrompt getInstance() {
		return ADD_USER;
	}

	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("Enter account number: ");
		long number = Long.parseLong(s.nextLine());
		if (!t.ValidAccount(number)) {
			System.out.println("Invalid account number.");
		} else {
			System.out.print("Enter username to add to account: ");
			String user = s.nextLine();
			if (!t.ValidUser(user)) {
				System.out.println("Invlid username.");
			} else {
				t.AddUserToAccount(number, user);
			}
		}
		return MainMenuPrompt.getInstance();
	}
}

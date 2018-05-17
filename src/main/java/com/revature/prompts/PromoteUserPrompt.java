package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class PromoteUserPrompt implements Prompt {
	
	private static PromoteUserPrompt PROMOTE = new PromoteUserPrompt();
	
	private PromoteUserPrompt() {
	}
	
	public static PromoteUserPrompt getInstance() {
		return PROMOTE;
	}

	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("Enter user name: ");
		String user = s.nextLine();
		if (!t.ValidUser(user)) {
			System.out.println("Invalid user name.");
		} else {
			t.PromoteUser(user);
		}
		return MainMenuPrompt.getInstance();
	}
}

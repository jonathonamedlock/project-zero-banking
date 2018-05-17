package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class LoginPrompt implements Prompt {

	private static final LoginPrompt LOGIN = new LoginPrompt();
	
	private LoginPrompt() {
	}
	
	public static LoginPrompt getInstance() {
		return LOGIN;
	}
	
	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("Enter <username>: ");
		String name = s.nextLine();
		System.out.print("Enter <password>: ");
		String pass = s.nextLine();
		if (t.LogIn(name, pass)) {
			return MainMenuPrompt.getInstance();
		} else {
			System.out.println("Incorrect password or username.");
			return LoginRegisterPrompt.getInstance();
		}
		
	}
	
}

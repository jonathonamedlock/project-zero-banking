package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class LoginRegisterPrompt implements Prompt {
	
	private static final LoginRegisterPrompt LOGIN_REGISTER = new LoginRegisterPrompt();
	
	private LoginRegisterPrompt() {
	}
	
	public static LoginRegisterPrompt getInstance() {
		return LOGIN_REGISTER;
	}

	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("(L)ogin or (R)egister?: ");
		String input = s.nextLine().toUpperCase();
		switch (input) {
		// if L
			case "L":
				return LoginPrompt.getInstance();
		// if R
			case "R":
				return RegisterPrompt.getInstance();
		// if bad input
			default:
				System.out.println("Please try again.");
				return LoginRegisterPrompt.getInstance();
		}
	}

}

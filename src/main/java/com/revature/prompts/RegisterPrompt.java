package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class RegisterPrompt implements Prompt {
	public static final RegisterPrompt REGISTER = new RegisterPrompt();
	
	private RegisterPrompt() {
	}
	
	public static RegisterPrompt getInstance() {
		return REGISTER;
	}
	
	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("Enter <username>: ");
		String name = s.nextLine();
		System.out.print("Enter <password>: "); 
		String pass = s.nextLine();
		System.out.print("Confirm <password>: ");
		String confirm = s.nextLine();
		if (t.NewUser(name, pass, confirm)) {
			t.LogIn(name, pass);
			return MainMenuPrompt.getInstance();
		} else {
			System.out.println("Registration failed. Either passwords did not match, username taken, or entry improperly formatted.");
			return LoginRegisterPrompt.getInstance();
		}
		
	}
}

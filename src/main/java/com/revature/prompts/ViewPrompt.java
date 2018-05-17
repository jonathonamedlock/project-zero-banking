package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class ViewPrompt implements Prompt {
	public static final ViewPrompt VIEW = new ViewPrompt();
	
	private ViewPrompt() {
	}
	
	public static ViewPrompt getInstance() {
		return VIEW;
	}
	
	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("View history of which user: ");
		String user = s.nextLine();
		if (!t.HasMultiple(user)) {
			
		}
		return MainMenuPrompt.getInstance();
	}
}

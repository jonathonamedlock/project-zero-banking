package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class MainMenuPrompt implements Prompt{
	
	public static final MainMenuPrompt MAIN_MENU = new MainMenuPrompt();
	
	private MainMenuPrompt() {
	}
	
	public static MainMenuPrompt getInstance() {
		return MAIN_MENU;
	}

	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("(D)eposit, (W)ithdraw, (C)heck balance, (V)iew history, (A)dd new account, e(X)it");
		if (t.getActive().isAdmin()) {
			System.out.print("\nv(I)ew a user's history, add (U)ser to existing account: ");
		} else {
			System.out.print(": ");
		}
		String input = s.nextLine().toUpperCase();
		switch (input) {
			//if D
			case "D":
				return DepositPrompt.getInstance();
			//if W
			case "W":
				return WithdrawPrompt.getInstance();
			//if C
			case "C":
				//Format and display balance
				return MainMenuPrompt.getInstance();
			//if V
			case "V":
				//Format and display the history
				return MainMenuPrompt.getInstance();
			//if A
			case "A":
				// return NewAccountPrompt.getInstance();
			//if X
			case "X":
				return LoginRegisterPrompt.getInstance();
			//if I
			case "I":
				if (t.getActive().isAdmin()) {
					return ViewPrompt.getInstance();
				}
			//if U
			case "U":
				if (t.getActive().isAdmin()) {
					// return AddUserToAccount.getInstance();
				}
			default:
				System.out.println("Incorrect selection. Please try again.");
				return MainMenuPrompt.getInstance();
		}
	}

}

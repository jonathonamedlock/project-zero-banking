package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class MainMenuPrompt implements Prompt{
	
	private static final MainMenuPrompt MAIN_MENU = new MainMenuPrompt();
	
	private MainMenuPrompt() {
	}
	
	public static MainMenuPrompt getInstance() {
		return MAIN_MENU;
	}

	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("(D)eposit, (W)ithdraw, (C)heck balance, (V)iew history, (A)dd new account, e(X)it");
		if (t.getActive().isAdmin()) {
			System.out.print("\nv(I)ew a user account's history, add (U)ser to existing account, (P)romote user to admin: ");
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
				System.out.println(t.ViewBalance());
				return MainMenuPrompt.getInstance();
			//if V
			case "V":
				if (t.getActive().AccountCount() == 1) {
					//Format and display the history
					System.out.println(t.TransactionHistory());
					return MainMenuPrompt.getInstance();
				} else {
					SelectorPrompt.getInstance().SelectAccount(t, s);
					System.out.println(t.TransactionHistory(SelectorPrompt.getInstance().getActive()));
					SelectorPrompt.getInstance().clear();
					return MainMenuPrompt.getInstance();
				}
			//if A
			case "A":
				String newAccountNumber = t.AddAccount();
				if (newAccountNumber != null) {
					System.out.println("Created new account for " + t.getActive().getUsername() + " with account number " + newAccountNumber);
				} else {
					System.err.println("Error creating new account");
				}
				return MainMenuPrompt.getInstance();
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
					return AddUserToAccountPrompt.getInstance();
				}
			case "P":
				if (t.getActive().isAdmin()) {
					return PromoteUserPrompt.getInstance();
				}
			default:
				System.out.println("Invalid selection. Please try again.");
				return MainMenuPrompt.getInstance();
		}
	}

}

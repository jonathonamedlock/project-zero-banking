package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class DepositPrompt implements Prompt {
	public static final DepositPrompt DEPOSIT = new DepositPrompt();
	
	private DepositPrompt() {
	}
	
	public static DepositPrompt getInstance() {
		return DEPOSIT;
	}
	
	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("Deposit: ");
		double amount = Double.parseDouble(s.nextLine());
		if (!t.HasMultiple()) {
			if(!t.Deposit(amount)) {
				System.out.println("Error depositing. Attempted amount was negative.");
			} else {
				System.out.println("Deposit success.");
			}
		} else {
			System.out.print("Enter account number: ");
			String accNum = s.nextLine();
		}
		return MainMenuPrompt.getInstance();
	}
}

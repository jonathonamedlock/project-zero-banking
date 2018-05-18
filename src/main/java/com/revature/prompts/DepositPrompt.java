package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class DepositPrompt implements Prompt {
	private static final DepositPrompt DEPOSIT = new DepositPrompt();
	
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
				System.out.println("Error depositing. Attempted amount was zero or negative.");
			} else {
				System.out.println("Deposit success.");
			}
		} else {
			SelectorPrompt.getInstance().SelectAccount(t, s);
			if (t.getActive().getAccount(SelectorPrompt.getInstance().getActive()) == null) {
				System.out.println("Account with that number not found.");
				return DepositPrompt.getInstance();
			}
			if (!t.Deposit(amount, SelectorPrompt.getInstance().getActive())) {
				System.out.println("Error depositing. Attempted amount was zero or negative.");
			} else {
				System.out.println("Deposit success.");
			}
			SelectorPrompt.getInstance().clear();
		}
		return MainMenuPrompt.getInstance();
	}
}

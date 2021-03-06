package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class WithdrawPrompt implements Prompt {
	private static final WithdrawPrompt WITHDRAW = new WithdrawPrompt();
	
	private WithdrawPrompt() {
	}
	
	public static WithdrawPrompt getInstance() {
		return WITHDRAW;
	}
	
	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("Withdraw: ");
		double amount = Double.parseDouble(s.nextLine());
		if (t.getActive().AccountCount() == 1) {
			if(!t.Withdraw(amount)) {
				System.out.println("Error withdrawing. Either attempted amount was zero or negative or the total funds were insufficient.");
			} else {
				System.out.println("Withdraw success.");
			}
		} else {
			SelectorPrompt.getInstance().SelectAccount(t, s);
			if (t.getActive().getAccount(SelectorPrompt.getInstance().getActive()) == null) {
				System.out.println("Account with that number not found.");
				return WithdrawPrompt.getInstance();
			}
			if (!t.Withdraw(amount, SelectorPrompt.getInstance().getActive())) {
				System.out.println("Error withdrawing. Either attempted amount was zero or negative or the total funds were insufficient.");
			} else {
				System.out.println("Withdraw success.");
			}
			SelectorPrompt.getInstance().clear();
		}
		return MainMenuPrompt.getInstance();
	}
}

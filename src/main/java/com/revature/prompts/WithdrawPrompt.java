package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public class WithdrawPrompt implements Prompt {
	public static final WithdrawPrompt WITHDRAW = new WithdrawPrompt();
	
	private WithdrawPrompt() {
	}
	
	public static WithdrawPrompt getInstance() {
		return WITHDRAW;
	}
	
	@Override
	public Prompt run(Terminal t, Scanner s) {
		System.out.print("Withdraw: ");
		double amount = Double.parseDouble(s.nextLine());
		if(!t.Withdraw(amount)) {
			System.out.println("Error withdrawing. Either attempted amount was negative or the total funds were insufficient.");
		} else {
			System.out.println("Withdraw success.");
		}
		return MainMenuPrompt.getInstance();
	}
}

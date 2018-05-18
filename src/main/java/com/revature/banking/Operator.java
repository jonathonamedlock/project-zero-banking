package com.revature.banking;

import java.util.Scanner;

import com.revature.prompts.LoginRegisterPrompt;
import com.revature.prompts.Prompt;

public class Operator {
	public static void main(String[] args) {
		// This is where the prompts go. 
		// Pass them the Terminal to handle
		Scanner scan = new Scanner (System.in);
		Prompt p = LoginRegisterPrompt.getInstance();
		while (true) {
			p = p.run(Terminal.getInstance(), scan);
		}
	}
}

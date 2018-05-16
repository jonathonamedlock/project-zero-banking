package com.revature.banking;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import com.revature.prompts.LoginRegisterPrompt;
import com.revature.prompts.Prompt;

public class TestPrompts {

	@Test
	public void test() {
		Terminal t = new Terminal();
		Prompt p = LoginRegisterPrompt.getInstance();
		while (true) {
			p = p.run(t, new Scanner(System.in));
		}
	}

}

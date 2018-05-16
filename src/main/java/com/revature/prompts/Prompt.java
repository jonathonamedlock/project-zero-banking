package com.revature.prompts;

import java.util.Scanner;

import com.revature.banking.Terminal;

public interface Prompt {
	Prompt run(Terminal t, Scanner s);
}

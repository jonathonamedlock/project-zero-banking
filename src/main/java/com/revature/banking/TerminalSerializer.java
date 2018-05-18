package com.revature.banking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminalSerializer{
	private static final TerminalSerializer serializer = new TerminalSerializer();
	private Map<User, User> users;
	private Map<Account, List<User>> accounts;
	
	private TerminalSerializer() {
		users = new HashMap<>();
		accounts = new HashMap<>();
	}
	
	public static TerminalSerializer getInstance() {
		return serializer;
	}
	
	public void WriteUsers(Map<User,User> users) {
		if (!new File("users.txt").exists()) {
			try {
				new File("users.txt").createNewFile();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("users.txt"))) {
			writer.writeInt(users.size());
			for (User u : users.keySet()) {
				writer.writeObject(u);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void Read() {
		if (!new File("users.txt").exists()) {
			try {
				new File("users.txt").createNewFile();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			users = null;
			accounts = null;
			return;
		}
		try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream("users.txt"))) {
			int count = reader.readInt();
			for (int i = 0; i < count; i++) {
				User u = (User) reader.readObject();
				users.put(u, u);
				for (int j = 0; j < u.AccountCount(); j++) {
					if (!accounts.containsKey(u.getAccount(j))) {
						accounts.put(u.getAccount(j), new ArrayList<User>());
					}
					accounts.get(u.getAccount(j)).add(u);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			if (users.size() == 0) {
				users = null;
				accounts = null;
			}
			// System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Map<Account, List<User>> getUserAccounts() {
		return accounts;
	}
	
	public Map<User, User> getUsers() {
		return users;
	}
}

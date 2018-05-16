package com.revature.banking;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TerminalSerializer{
	public static final TerminalSerializer serializer = new TerminalSerializer();
		
	private TerminalSerializer() {}
	
	public void SaveUser(Set<User> users) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("userList.txt"))) {
			for (User u : users) {
				out.writeObject(u);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SaveAccount(Map<User, List<Account> > accounts) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("accountList.txt"))) {
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User ReadUser() {
		return null;
	}
	
	public Account ReadAccount() {
		return null;
	}
}

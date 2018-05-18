package com.revature.banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicLong;

public class AccountNumberSerializer {
	private static final AccountNumberSerializer serializer = new AccountNumberSerializer();
	
	private AccountNumberSerializer() {}
	
	public static AccountNumberSerializer getInstance() {
		return serializer;
	}
	
	public void Write(AtomicLong currentNumber) {
		try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("accID.txt"))) {
			writer.writeObject(currentNumber);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AtomicLong Read() {
		try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream("accID.txt"))) {
			return (AtomicLong) reader.readObject();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}

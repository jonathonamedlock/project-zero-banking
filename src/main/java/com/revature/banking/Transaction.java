package com.revature.banking;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2139776994171253256L;
	private boolean isDeposit;
	private double before;
	private double after;
	private LocalDateTime time;
	private String byUser;
	
	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public void setDeposit(boolean isDeposit) {
		this.isDeposit = isDeposit;
	}

	public void setBefore(double before) {
		this.before = before;
	}

	public void setAfter(double after) {
		this.after = after;
	}

	public Transaction(boolean isDeposit, double before, double after) {
		super();
		this.isDeposit = isDeposit;
		this.before = before;
		this.after = after;
		time = LocalDateTime.now();
		byUser = null;
	}
	
	public Transaction(boolean isDeposit, double before, double after, String by) {
		this (isDeposit, before, after);
		byUser = by;
	}
	
	public boolean isDeposit() {
		return isDeposit;
	}
	
	public double getBefore() {
		return before;
	}
	
	public double getAfter() {
		return after;
	}

	@Override
	public String toString() {
		StringBuilder history = new StringBuilder();
		if (isDeposit) {
			history.append("D");
		} else {
			history.append("W");
		}
		history.append('\t');
		history.append(String.format("%.2f", before));
		history.append("\t\t");
		history.append(String.format("%.2f", after));
		history.append("\t\t");
		history.append(time);
		if (byUser != null) {
			history.append('\t');
			history.append(byUser);
		}
		return history.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(after);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(before);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (isDeposit ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(after) != Double.doubleToLongBits(other.after))
			return false;
		if (Double.doubleToLongBits(before) != Double.doubleToLongBits(other.before))
			return false;
		if (isDeposit != other.isDeposit)
			return false;
		return true;
	}
}

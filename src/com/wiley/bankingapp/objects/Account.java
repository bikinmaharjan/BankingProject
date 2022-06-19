package com.wiley.bankingapp.objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Account {
	private static int count = 0;
	private int accountNumber;
	private double balance;
	private String dateOpened;
	private String bankName;
	private String branchName;
	private int customerId;
	

	public Account() {

	}
	
	public Account(String bankName, String branchName, double balance) {
		Account.count++;
		this.accountNumber = count;
		this.balance = balance;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
		this.dateOpened = formatter.format(cal.getTime());
		this.bankName = bankName;
		this.branchName = branchName;
	}
	
//	public Account(String bankName, String branchName, String name, int age, long mobileNumber, String aadharNumber) {
//		Account.count++;
//		this.accountNumber = count;
//		this.balance = 100;
//		this.dateOpened = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
//		this.bankName = bankName;
//		this.branchName = branchName;
//		new Customer(name, age, mobileNumber, aadharNumber);
//	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(String dateOpened) {
		this.dateOpened = dateOpened;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	protected abstract double calculateInterest(Account ac);

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", dateOpened=" + dateOpened
				+ ", bankName=" + bankName + ", branchName=" + branchName + ", customerId=" + customerId + "]";
	}

	
	
	
}

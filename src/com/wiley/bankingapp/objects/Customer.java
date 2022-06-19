package com.wiley.bankingapp.objects;

import java.util.ArrayList;

import com.wiley.bankingapp.exceptions.InvalidCharacterException;
import com.wiley.bankingapp.utility.DAO;
import com.wiley.bankingapp.utility.Utility;

public class Customer {
	private static int count = 0;
	private int customerId;
	private String name;
	private int age;
	private long mobileNumber;
	private String aadharNumber;
	private SavingsAccount savingsAccount;
	private ArrayList<FixedDeposit> fixedDeposit;

	public Customer() {
	}

	public Customer(int id,String name, int age, long mobileNumber, String aadharNumber) {
		this.customerId = id;
		this.name = name;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.aadharNumber = aadharNumber;
	}

	public Customer(String name, int age, long mobileNumber, String aadharNumber, String bankName, String branchName) throws InvalidCharacterException {
		Utility util = new Utility();
		if (util.isValidName(name)) {
			this.name = name;
			Customer.count++;
			this.customerId = count;
			this.age = age;
			this.mobileNumber = mobileNumber;
			this.aadharNumber = aadharNumber;
			

		} else {
			InvalidCharacterException e = new InvalidCharacterException("Invalid Character for Name. Don't use numbers or Special Characters");
			throw e;
		}

	}

	public Customer(String name, int age, long mobileNumber, String aadharNumber, SavingsAccount savingsAccount,
			ArrayList<FixedDeposit> fixedDeposit) throws InvalidCharacterException {
		Utility util = new Utility();
		DAO dao = new DAO();
		Customer.count = dao.getLastCustomerID();

		if (util.isValidName(name)) {
			this.name = name;
			Customer.count++;
			this.customerId = count;
			this.age = age;
			this.mobileNumber = mobileNumber;
			this.aadharNumber = aadharNumber;
			this.setSavingsAccount(savingsAccount);
			this.setFixedDeposit(fixedDeposit);
			dao.customerDBEntry(this.customerId, this.name, this.age, this.mobileNumber, this.aadharNumber);
		} else {
			InvalidCharacterException e = new InvalidCharacterException("Invalid Character for Name. Don't use numbers or Special Characters");
			throw e;
		}
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", age=" + age + ", mobileNumber="
				+ mobileNumber + ", aadharNumber=" + aadharNumber + "\n\tsavingsAccount=" + savingsAccount
				+ "\n\tfixedDeposit=" + fixedDeposit + "\n\t\t]\n";
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	public ArrayList<FixedDeposit> getFixedDeposit() {
		return fixedDeposit;
	}

	public void setFixedDeposit(ArrayList<FixedDeposit> fixedDeposit) {
		this.fixedDeposit = fixedDeposit;
	}

}

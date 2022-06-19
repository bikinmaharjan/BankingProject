package com.wiley.bankingapp.objects;

import com.wiley.bankingapp.exceptions.InsufficientBalanceException;
import com.wiley.bankingapp.utility.CurrencyConvertor;



public class SavingsAccount extends Account implements CurrencyConvertor{
	private boolean isSalaryAccount;
	private double minBalance = 100;
	private double interestRate = 4;
	private double currencyConversionRate = 2.0;
	private double USDToAUDRate = 1.4;
	private double AUDToUSDRate = 0.69;
	private double USDToSGDRate = 1.39;
	private double SGDToUSDRate = 0.72;
	
	
	
	public SavingsAccount() {
		
	}
	
	public SavingsAccount(String bankName, String branchName,boolean isSalaryAccount, double balance) throws InsufficientBalanceException {
		super(bankName,branchName,balance);
		this.isSalaryAccount = isSalaryAccount; 
		if (isSalaryAccount) {
			this.minBalance = 0;
			this.currencyConversionRate = 1;
		}
		
		if(balance >= minBalance) {
			super.setBalance(balance);
		} else {
//			System.err.println("Insufficient Balance for account creation. Minimum balance is $" + minBalance);
			InsufficientBalanceException e = new InsufficientBalanceException("Not enough balance");
			throw e;
		}
	}
	
	public boolean isSalaryAccount() {
		return isSalaryAccount;
	}

	public void setSalaryAccount(boolean isSalaryAccount) {
		this.isSalaryAccount = isSalaryAccount;
	}

	public double deposit(double amount) {
		return super.getBalance() + amount;
	}
	
	public double withdraw(double amount) throws InsufficientBalanceException {
		System.out.println("Your initial balance is " + super.getBalance());
		if ((super.getBalance() - amount) >= minBalance) {	
			double remainingBalance = super.getBalance() - amount;
			System.out.println("After withdrawing " + amount + ". Your balance is " + remainingBalance);
			return remainingBalance;
		} else {
			InsufficientBalanceException e = new InsufficientBalanceException("You should have atleast " + minBalance + " in your account. You are trying to withdraw " + amount + ", but your balance is " + this.getBalance());
			throw e;
		}
	}
	
	@Override
	public double calculateInterest(Account ac) {
		return (ac.getBalance())* (interestRate/100);
		
	}
	
	@Override
	public String toString() {
		return "customerId: " + super.getCustomerId() +" Saving acNumber: " + super.getAccountNumber() + " Balance: " + super.getBalance() + " DateOpened: " + this.getDateOpened() + " Is Salary Account: " + this.isSalaryAccount;
		
	}

	@Override
	public double convertUSDToAUD(double USD) {
		return ((USD * USDToAUDRate) + (currencyConversionRate * USD)/100 );
	}

	@Override
	public double convertAUDToUSD(double AUD) {
		return ((AUD * AUDToUSDRate) + (currencyConversionRate * AUD)/100 );
	}

	@Override
	public double convertUSDToSGD(double USD) {
		return ((USD * USDToSGDRate) + (currencyConversionRate * USD)/100 );
	}

	@Override
	public double convertSGDToUSD(double SGD) {
		return ((SGD * SGDToUSDRate) + (currencyConversionRate * SGD)/100 );
	}
	
}

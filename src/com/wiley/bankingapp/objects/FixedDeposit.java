package com.wiley.bankingapp.objects;

import com.wiley.bankingapp.exceptions.InsufficientBalanceException;

public class FixedDeposit extends Account {
	private double depositAmount;
	private int tenure;
	private double interestEarned;
	private double minDepositAmount = 50;
	private double minTenure = 1;
	private double maxTenure = 5;
	private int seniorCitizenAge = 45;
	private double interestRateNC2 = 4.00;
	private double interestRateNC4 = 5.00;
	private double interestRateNC5 = 6.00;
	private double interestRateSC2 = 6.50;
	private double interestRateSC4 = 5.50;
	private double interestRateSC5 = 6.50;
	
	
	
	public FixedDeposit() {
		// TODO Auto-generated constructor stub
	}
	public FixedDeposit(String bankName, String branchName, double depositAmount, int tenure, double interestEarned) throws InsufficientBalanceException {
		super(bankName,branchName, depositAmount);
		if(depositAmount > minDepositAmount) {
			this.depositAmount = depositAmount;
		} else {
			InsufficientBalanceException e = new InsufficientBalanceException("Deposit Amount should be atleast $" + minDepositAmount);
			throw e;
		}
		if (tenure >= minTenure && tenure <=maxTenure) {
			this.tenure = tenure;
		} else {
			System.err.println("The minimum tenure is " + minTenure + " year and the maximum is " + maxTenure + " years");
		}
		
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public double getInterestEarned() {
		return interestEarned;
	}
	public void setInterestEarned(double interestEarned) {
		this.interestEarned = interestEarned;
	}
	
	
	@Override
	public String toString() {
		return "\n\t\tFixedDeposit [customerId=" + super.getCustomerId() + " acNo=" + super.getAccountNumber() +" depositAmount=" + depositAmount + ", tenure=" + tenure + "]";
	}
	@Override
	public double calculateInterest(Account ac) {
		return 1.0;
	}
	public double calculateInterest(Account ac, int age) {
		if (age >= seniorCitizenAge) {
			if (this.tenure <=2) {
				this.interestEarned = ac.getBalance()* (interestRateSC2/100);
				return (ac.getBalance())* (interestRateSC2/100);
			} else if (this.tenure <=4) {
				return (ac.getBalance())* (interestRateSC4/100);
			} else {
				return (ac.getBalance())* (interestRateSC5/100);
			}
		} else {
			if (this.tenure <=2) {
				return (ac.getBalance())* (interestRateNC2/100);
			} else if (this.tenure <=4) {
				return (ac.getBalance())* (interestRateNC4/100);
			} else {
				return (ac.getBalance())* (interestRateNC5/100);
			}
		}

	}
}

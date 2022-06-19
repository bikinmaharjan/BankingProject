
import java.util.ArrayList;
import java.util.Scanner;

import com.wiley.bankingapp.exceptions.CustomerNotFoundException;
import com.wiley.bankingapp.exceptions.InsufficientBalanceException;
import com.wiley.bankingapp.exceptions.InvalidCharacterException;
import com.wiley.bankingapp.objects.Customer;
import com.wiley.bankingapp.objects.FixedDeposit;
import com.wiley.bankingapp.objects.SavingsAccount;
import com.wiley.bankingapp.utility.DAO;



public class TestBankingSystem {
	DAO dao = new DAO();
	public static void main(String[] args) {
		TestBankingSystem tbs = new TestBankingSystem();
		
		tbs.listCustomers();
//		tbs.createCustomerWithAccount();
		try {
			tbs.searchCustomer();
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void listCustomers() {
		System.out.println("List of customers in DB");
		System.out.println("<____________________________________________________________________________>");
		
		ArrayList <Customer> customers = dao.getAllCustomers();
		System.out.println(customers);
	}
	
	@SuppressWarnings("resource")
	public void searchCustomer() throws CustomerNotFoundException {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Customer ID to search for in DB: ");
		int id = in.nextInt();
		System.out.println("<____________________________________________________________________________>");
		if (dao.getCustomer(id).getCustomerId() == 0) {
			CustomerNotFoundException e = new CustomerNotFoundException("No customer with that ID");
			throw e;
		} else {
			System.out.println(dao.getCustomer(id));
		}
		in.close();
	}
	
	
	public void createCustomerWithAccount() {
		try {			
			SavingsAccount sa1 = new SavingsAccount("COM", "Auburn",true, 3000);
			FixedDeposit fd1 = new FixedDeposit("COM", "Auburn",300,1,4);
			FixedDeposit fd2 = new FixedDeposit("NAB", "Hurstville",400,2,3);
			ArrayList<FixedDeposit> fd = new ArrayList<>();
			fd.add(fd1);
			fd.add(fd2);
			Customer c3 = new Customer("Karl Kent", 29, 0000000000, "Alien", sa1, fd);
			sa1.setCustomerId(c3.getCustomerId());
			fd1.setCustomerId(c3.getCustomerId());
			fd2.setCustomerId(c3.getCustomerId());
			
			System.out.println("<____________________________________________________________________________>");
			System.out.println(c3);
			
			System.out.println("_________________________________________________");
			System.out.println("Interest Earned from Savings account is $" + sa1.calculateInterest(sa1));
			fd.forEach((f)-> {
				double interestEarned =  f.calculateInterest(f,c3.getAge());
				f.calculateInterest(f);
				System.out.println("Interest Earned from Fixed Deposit account " + f.getAccountNumber() + " is $" +interestEarned );
			});
			System.out.print("Converting 3000 AUD to USD: ");
			System.out.println(sa1.convertAUDToUSD(3000));
			System.out.println("_________________________________________________");
			sa1.withdraw(3000);
		} catch (InsufficientBalanceException e){
			System.out.println(e.getMessage());
		} catch (InvalidCharacterException e) {
			System.out.println(e.getMessage());
		}	
	}

}

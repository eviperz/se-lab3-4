package atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * An ATM that accesses a bank.
 */
@Component
public class ATM {
	@Autowired
	private Bank bank;
	private Customer loginCustomer;

	/**
	 * Constructs an ATM for a bank.
	 */

	/**
	 * Finds customer in bank.
	 * @param id current customer id
	 * @param pin pin being inputted
	 */
	public String validateCustomer(int id, int pin) {
		Customer customer = bank.findCustomer(id);

		if (customer != null && customer.checkPin(pin)) {
			loginCustomer = customer;
			return loginCustomer.getName();
		}
		return null;
	}

	/**
	 * Withdraws amount from current account.
	 * @param amount the amount to withdraw
	 */
	public void withdraw(double amount) {
		loginCustomer.getAccount().withdraw(amount);
	}

	/**
	 * Deposits amount to current account.
	 * @param amount the amount to deposit
	 */
	public void deposit(double amount) {
		loginCustomer.getAccount().deposit(amount);
	}

	/**
	 * Gets the balance of the current account.
	 * @return the balance
	 */
	public double getBalance() {
		return loginCustomer.getAccount().getBalance();
	}

	/**
	 * Transfer from current customer to the customer with
	 * customer id in the parameter
	 * @param receivingId receiver customer
	 * @param amount amount to be transferred
	 */
	public void transfer(int receivingId, double amount) {
		Customer receivingCustomer = bank.findCustomer(receivingId);
		loginCustomer.getAccount().withdraw(amount);
		receivingCustomer.getAccount().deposit(amount);
	}


	/**
	 * Resets the ATM to the initial state.
	 */
	public void end() {
		loginCustomer = null;
	}

}

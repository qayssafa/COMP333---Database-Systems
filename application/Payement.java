package application;

public class Payement {
	private int Payement_ID;
	private String Currency;
	private String Payment_Method;
	private double Amount;
	
	public Payement(){
		super();
	}

	public Payement(int payement_ID, String currency, String payment_Method, double amount) {
		super();
		this.Payement_ID = payement_ID;
		this.Currency = currency;
		this.Payment_Method = payment_Method;
		this.Amount = amount;
	}

	
	public int getPayement_ID() {
		return Payement_ID;
	}
	public void setPayement_ID(int payement_ID) {
		Payement_ID = payement_ID;
	}

	
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}

	
	public String getPayment_Method() {
		return Payment_Method;
	}
	public void setPayment_Method(String payment_Method) {
		Payment_Method = payment_Method;
	}

	
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
}

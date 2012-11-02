package core;

public class Payment {
	
	private String type;
	private double amount;
	
	public Payment(double amount, String type) {
		this.setType(type);
		this.setAmount(amount);
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

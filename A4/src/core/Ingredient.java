package core;

public class Ingredient {
	private double priceToPurchase;
	private double priceToCustomer;
	private double amountInStock;
	private String units;
	protected String name;

	public Ingredient(String name, String units, double purchasePrice) {
		if(name != "") {
			this.name = name;
		}
		else {
			name = "default";
		}
		setPriceToPurchase(purchasePrice);
		setPriceToCustomer(1.4*getPriceToPurchase()); // a healthy margin!
		this.setUnits(units);
		this.setAmountInStock(0);
	}
	
	public Ingredient(String name, String units, double purchasePrice, double amountInStock) {
		if(name != "") {
			this.name = name;
		}
		else {
			name = "default";
		}
		setPriceToPurchase(purchasePrice);
		setPriceToCustomer(1.4*getPriceToPurchase()); // a healthy margin!
		this.setUnits(units);
		this.setAmountInStock(amountInStock);
	}
	
	public String getName() {
		if(name != null && name != "") {
			return name;
		}
		else
			return "Ingredient not initialized.";
	}
	
	public String toString() {
		return name + " Price: " + getPriceToPurchase() + " In Stock: " + getAmountInStock() + " " + getUnits();
	}
	
	public String toSave() {
		return name + "_" + getUnits() + "_" + getPriceToPurchase() + "_" + getAmountInStock();
	}
	
	public double purchase(double amount) {
		double cost = amount*getPriceToPurchase();
		setAmountInStock(getAmountInStock() + amount);
		return cost; // allows for $$ to be subtracted from store, in case we keep track. Also, this method implies we can go into debt
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public double getPriceToPurchase() {
		return priceToPurchase;
	}

	public void setPriceToPurchase(double priceToPurchase) {
		this.priceToPurchase = priceToPurchase;
	}

	public double getPriceToCustomer() {
		return priceToCustomer;
	}

	public void setPriceToCustomer(double priceToCustomer) {
		this.priceToCustomer = priceToCustomer;
	}

	public double getAmountInStock() {
		return amountInStock;
	}

	public void setAmountInStock(double amountInStock) {
		this.amountInStock = amountInStock;
	}
}

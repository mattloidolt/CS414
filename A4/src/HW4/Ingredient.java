package HW4;

public class Ingredient {
	protected double priceToPurchase;
	protected double priceToCustomer;
	protected double amountInStock;
	protected String units;
	private String name;

	public Ingredient(String name, String units, double purchasePrice) {
		if(name != "") {
			this.name = name;
		}
		else {
			name = "default";
		}
		priceToPurchase = purchasePrice;
		priceToCustomer = 1.4*priceToPurchase; // a healthy margin!
		this.units = units;
	}
	
	public Ingredient(String name, String units, double purchasePrice, double amountInStock) {
		if(name != "") {
			this.name = name;
		}
		else {
			name = "default";
		}
		priceToPurchase = purchasePrice;
		priceToCustomer = 1.4*priceToPurchase; // a healthy margin!
		this.units = units;
		this.amountInStock = amountInStock;
	}
	
	public String getName() {
		if(name != null && name != "") {
			return name;
		}
		else
			return "Ingredient not initialized.";
	}
	
	public String toString() {
		return name + " Price: " + priceToPurchase + " In Stock: " + amountInStock + units;
	}
	
	public String toSave() {
		return name + "_" + units + "_" + priceToPurchase + "_" + amountInStock;
	}
	
	public double purchase(double amount) {
		double cost = amount*priceToPurchase;
		amountInStock += amount;
		return cost; // allows for $$ to be subtracted from store, in case we keep track. Also, this method implies we can go into debt
	}
}

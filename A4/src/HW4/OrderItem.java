package HW4;

public class OrderItem {

	MenuItem item;
	private int quantity;
	
	public OrderItem(MenuItem item) {
		this.item = item;
		this.setQuantity(1);
	}
	
	public void incrementItemQuantity() {
		this.setQuantity(this.getQuantity() + 1);
	}
	
	public boolean equals(MenuItem menuItem) {
		return this.item == menuItem;
	}
	
	public MenuItem getItem(){
		return item ;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}








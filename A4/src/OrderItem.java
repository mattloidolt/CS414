
public class OrderItem {

	MenuItem item;
	int quantity;
	
	public OrderItem(MenuItem item) {
		this.item = item;
		this.quantity = 1;
	}
	
	public void incrementItemQuantity() {
		this.quantity++;
	}
	
	public boolean equals(MenuItem menuItem) {
		return this.item == menuItem;
	}
}

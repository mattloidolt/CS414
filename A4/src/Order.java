import java.util.ArrayList;


public class Order {
	int orderID;
	int numberOfItems;
	double total;
	double paid;
	boolean prepared;
	boolean delivered;
	double amountDue;
	ArrayList<OrderItem> orderList = new ArrayList<OrderItem>();
	
	
	public Order() {
		
	}
	
	public void addItem(MenuItem item) {
		boolean found = false;
		for(OrderItem orderItem : orderList) {
			if (orderItem.equals(item)) {
				found = true;
				orderItem.incrementItemQuantity();
				break;
			}
		}
			
		if(!found) {
			this.orderList.add(new OrderItem(item));
		}
	}
	
	public void removeItem(MenuItem item) {
		OrderItem orderItem = new OrderItem(item);
		this.orderList.remove(orderItem);
	}
	
	public void pay(double amount, String type) {
		Payment payment = new Payment(amount, type);
		this.amountDue -= payment.amount;
		
	}
	

}

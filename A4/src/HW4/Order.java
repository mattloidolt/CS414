package HW4;
import java.util.ArrayList;


public class Order {
	int orderID;
	int numberOfItems;
	double total;
	double paid;
	boolean prepared;
	boolean delivered;
	double amountDue = 0.0;
	ArrayList<OrderItem> orderList = new ArrayList<OrderItem>();
	
	
	public Order() {
		
	}
	
	public void addItem(MenuItem item) {
		boolean found = false;
		for(OrderItem orderItem : orderList) {
			if (orderItem.equals(item)) {
				found = true;
				orderItem.incrementItemQuantity();
				this.amountDue += item.price;
				break;
			}
		}
			
		if(!found) {
			this.orderList.add(new OrderItem(item));
			this.amountDue += item.price;
		}
	}
	
	public void removeItem(MenuItem item) {
		for(OrderItem orderItem : this.orderList) {
			if(orderItem.equals(item)) {
				orderList.remove(orderItem);
				break;
			}
		}
	}
	
	public void pay(double amount, String type) {
		Payment payment = new Payment(amount, type);
		this.amountDue -= payment.amount;
	}
	
	public ArrayList<OrderItem> getOrderList() {
		return this.orderList;
	}
	
	public double getTotalDue() {
		double total = 0;
		for(OrderItem item : this.orderList) {
			total += item.item.price*item.quantity;
		}
		return total;
	}
	
	public double getAmountDue() {
		return this.amountDue;
	}

}

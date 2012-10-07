package HW4;
import java.util.ArrayList;


public class Order {
	int orderID;
	int numberOfItems;
	double total = 0.0;
	double paid;
	boolean prepared;
	boolean delivered;
	double amountDue = 0.0;
	ArrayList<OrderItem> orderList = new ArrayList<OrderItem>();
	
	
	public Order() {
		
	}
	
	public boolean addItem(MenuItem item) {
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
			this.total += item.price ;
			numberOfItems++ ;
			return true ;
		}
		else
			return false ;
	}
	
	public boolean removeItem(MenuItem item) {
		for(OrderItem orderItem : this.orderList) {
			if(orderItem.equals(item)) {
				orderList.remove(orderItem);
				numberOfItems-- ;
				return true ;
			}
		}
		return false ;
	}
	
	public int getNumItems() {
		return numberOfItems ;
	}
	
	public void pay(double amount, String type) {
		Payment payment = new Payment(amount, type);
		this.amountDue -= payment.getAmount();
	}
	
	public ArrayList<OrderItem> getOrderList() {
		return this.orderList;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public double getAmountDue() {
		return this.amountDue;
	}
	
	public boolean isPaid() {
		if(amountDue >= 0)
			return true ;
		return false ;
	}

}

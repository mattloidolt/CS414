package core;
import java.util.ArrayList;


public class MenuItem {
	
	public String name ;
	public double price ;
	protected ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>() ;
	
	public MenuItem(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
//	public MenuItem(String name, double price, String[] ingredients) {
//		this.name = name;
//		this.price = price;
//	}
	
	public String getName() {
		return name;
	}
        
        public void setName(String newName) {
            this.name = newName;
        }

	public double getPrice() {
		return price;
	}
        
        public void setPrice(double newPrice) {
            this.price = newPrice;
        }
        
        public String toString() {
            String string = "";
            string += this.name + " " + this.price;
            return string;
        }
        
        public String getSaveString() {
            return this.name + "-" + this.price;
        }
}

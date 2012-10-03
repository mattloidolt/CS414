package HW4;
import java.util.ArrayList;


public class MenuItem {
	
	public String name ;
	public double price ;
	protected ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>() ;
	
	public MenuItem(String name, double price) {
		this.name = name;
		this.price = price;
	}

}

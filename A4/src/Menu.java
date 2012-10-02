import java.sql.Time;
import java.util.ArrayList;


public class Menu {
	protected int menuID ;
	public String menuName ;
	protected Time effectiveDate ;
	protected Time ineffectiveDate ;
	private Manager creatingManager;
	private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	
	public void addMenuItem(MenuItem item) {
		if (!menuItems.contains(item))
			menuItems.add(item);
	}
	
	public void removeMenuItem(MenuItem item) {
		menuItems.remove(item);
	}
	

}

package HW4;
import java.util.ArrayList;
import java.util.Calendar;


public class Menu {
	protected int menuID ;
	public String menuName ;
	protected Calendar effectiveDate ;
	protected Calendar ineffectiveDate ;
	private Manager creatingManager;
	private MenuItem special;
	private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	public Menu(String menuName, Calendar effectiveDate, Calendar ineffectiveDate, Manager creatingManager) {
		this.menuName = menuName;
		this.effectiveDate = effectiveDate;
		this.ineffectiveDate = ineffectiveDate;	
		this.creatingManager = creatingManager;
	}
	
	public Menu(String menuName, Manager creatingManager) {
		this.menuName = menuName;
		this.creatingManager = creatingManager;
	}
	
	public MenuItem getItemOfName(String name) {
		for(int i = 0; i < menuItems.size(); ++i) {
			if(menuItems.get(i).getName().equals(name)) {
				return menuItems.get(i);
			}
		}
		
		return null;
	}
	
	public Manager getCreatingManager(){
		return this.creatingManager;
	}
	
	public void setEffectiveDate(Calendar date) {
		this.effectiveDate = date;
	}
	
	public void setIneffectiveDate(Calendar date) {
		this.ineffectiveDate = date;
	}
	
	public void addMenuItem(MenuItem item) {
		if (!menuItems.contains(item))
			menuItems.add(item);
	}
	
	public void removeMenuItem(MenuItem item) {
		menuItems.remove(item);
	}
	
	public void setSpecial(MenuItem item) {
		this.special = item;
	}
	
	public Calendar getEffectiveDate() {
		return this.effectiveDate;
	}
	
	public Calendar getIneffectiveDate() {
		return this.ineffectiveDate;
	}
	
	public ArrayList<MenuItem> getMenuItems() {
		return this.menuItems;
	}
	
	public MenuItem getSpecial() {
		return this.special;
	}
	
	public int getNumberOfItems() {
		return menuItems.size();
	}
	
	public MenuItem getItem(int x) {
		if(x > -1 && x < menuItems.size()) {
			return menuItems.get(x);
		}
		else
			return null;
	}
	
	public String getName() {
		return menuName;
	}
}

package com.example.androidapp;

import java.util.ArrayList;

import android.R.layout;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import core.*;

public class MainActivity extends Activity {
	public static final String EXTRA_MESSAGE = "Message";
	Order order = new Order();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//TODO: Load menu
		//Temp load data 
		core.Menu menu = new core.Menu("Lunch");
		MenuItem item1 = new MenuItem("Pizza", 9.98);
		MenuItem item2 = new MenuItem("Coke", 2.11);
		MenuItem item3 = new MenuItem("Apple", 2.99);
		MenuItem item4 = new MenuItem("Sausage", 2.99);
		MenuItem item5 = new MenuItem("Burger", 2.99);
		MenuItem item6 = new MenuItem("Soup", 2.99);
		menu.addMenuItem(item1);
		menu.addMenuItem(item2);
		menu.addMenuItem(item3);
		menu.addMenuItem(item4);
		menu.addMenuItem(item5);
		menu.addMenuItem(item6);
		//End loading data
		this.populateMenuItemButtons(menu);

	}

	public void populateMenuItemButtons(core.Menu menu) {
		int screenWidth = this.getScreenWidth();
		int buttonWidth = 150;
		int currentXPosition = 0;
		int height = 50;
		
		ArrayList<MenuItem> items = menu.getMenuItems();
		RelativeLayout layout = (RelativeLayout) View.inflate(this, R.layout.activity_main, null); //Get current view
		
		for (int i = 0; i<items.size(); i++) {
			Button button = this.createButton(items.get(i).name, buttonWidth, menu);
			RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rel_btn.leftMargin = currentXPosition; //X position of button
			rel_btn.topMargin = height; //Y position of button
			
			currentXPosition += buttonWidth; //Update the current X position
			if(currentXPosition+buttonWidth > screenWidth){ //If the next button is forced off screen
				height += 100; //Go down one row
				currentXPosition = 0;
			}
			
			button.setLayoutParams(rel_btn); //Set the button's position
			layout.addView(button); //Add button to view
			setContentView(layout); //Show button
		}	   
	}
	
	
	Button createButton(final String name, int width, final core.Menu menu){
		Button button = new Button(this);
		button.setText(name);
		button.setWidth(width);
		
		button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MenuItem item = menu.getItemOfName(name);
                order.addItem(item);
            }
        });

		return button;
	}
	
	@SuppressLint("NewApi")
	int getScreenWidth() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size.x;
	}
	
	@SuppressLint("NewApi")
	int getScreenHeight() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size.y;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void viewOrder(View view){
		Intent intent = new Intent(this, ViewOrderActivity.class);
//		Button buttonText = (Button) findViewById(R.id.ViewOrderButton);
//		String message = buttonText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, order.toString());
		startActivity(intent);

	}

	public void placeOrder(View view){
		System.out.println("placeOrder");
	}

}

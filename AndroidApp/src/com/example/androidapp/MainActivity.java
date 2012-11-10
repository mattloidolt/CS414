package com.example.androidapp;

import java.util.ArrayList;
import controller.KioskCont;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	public static final String EXTRA_MESSAGE = "Message";
	ArrayList<String> order = new ArrayList<String>() ;
	String currentMenu = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// TODO: menu name needs to be gathered dynamically (maybe some sort of menu bar again?)
		KioskCont.setupDefaults();//TODO:Load Dynamic
		ArrayList<String> menuItemList = KioskCont.getDefaultMenuItems("Lunch");//TODO:Load Dynamic
		currentMenu = "Lunch"; //TODO:Load Dynamic
		//End loading data
		this.populateMenuItemButtons(menuItemList);
		
		setupMenuButtons();

	}
	
	public void setupMenuButtons() {
		Button prevButton = (Button) findViewById(R.id.previousButton);
		Button nextButton = (Button) findViewById(R.id.nextButton);
		TextView menuLabel = (TextView) findViewById(R.id.currentMenu);
		
		prevButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				currentMenu = KioskCont.getPreviousMenu(currentMenu);
				ArrayList<String> items = KioskCont.getDefaultMenuItems(currentMenu);
				populateMenuItemButtons(items);
			}
		});
		
		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				currentMenu = KioskCont.getNextMenu(currentMenu);
				ArrayList<String> items = KioskCont.getDefaultMenuItems(currentMenu);
				populateMenuItemButtons(items);
			}
		});
		
		menuLabel.setText(currentMenu);
	}

	public void populateMenuItemButtons(ArrayList<String> items) {
		int screenWidth = this.getScreenWidth();
		int buttonWidth = 150;
		int currentXPosition = 0;
		int height = 70;

		RelativeLayout layout = (RelativeLayout) View.inflate(this, R.layout.activity_main, null); //Get current view

		for (int i = 1; i<items.size(); i++) {
			Button button = this.createButton(items.get(i), buttonWidth);
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
		setupMenuButtons();
	}


	private Button createButton(final String name, int width){
		final String[] item = name.split("-") ;
		final String itemName = item[0];
		final double itemPrice = Double.parseDouble(item[1]);
		
		Button button = new Button(this);
		//Prevent multiple lines for names
		if(itemName.length() > 7){
			button.setText(itemName.subSequence(0, 5) + "...\n$" + itemPrice);
		} else
			button.setText(itemName + "\n$" + itemPrice);
		
		button.setWidth(width);

		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				KioskCont.addToOrder(itemName, itemPrice);
			}
		});

		return button;
	}

	@SuppressLint("NewApi")
	private int getScreenWidth() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size.x;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void viewOrder(View view){
		Intent intent = new Intent(this, ViewOrderActivity.class);
		intent.putExtra("Order", KioskCont.getOrder().toString());
		startActivity(intent);

	}

	public void placeOrder(View view){
		Intent intent = new Intent(this, PlaceOrder.class);
		startActivity(intent);
	}

	public void undo(View view){
		KioskCont.removeLastOrderItem();
	}

}

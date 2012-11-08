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
//	Order order = new Order();
	ArrayList<String> order = new ArrayList<String>() ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// TODO: menu name needs to be gathered dynamically (maybe some sort of menu bar again?)
		ArrayList<String> menu = KioskCont.getMenu("Lunch") ;
		//End loading data
		this.populateMenuItemButtons(menu);

	}

	public void populateMenuItemButtons(ArrayList<String> items) {
		int screenWidth = this.getScreenWidth();
		int buttonWidth = 150;
		int currentXPosition = 0;
		int height = 50;

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
	}


	private Button createButton(final String name, int width){
		Button button = new Button(this);
		button.setText(name);
		button.setWidth(width);

		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final String[] item = name.split("-") ;
				KioskCont.addToOrder(item[0], Double.parseDouble(item[1]));
//				order.add(item[0] + "   " + item[1]);
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

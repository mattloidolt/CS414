package com.example.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	public static final String EXTRA_MESSAGE = "Message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void viewOrder(View view){
    	Intent intent = new Intent(this, ViewOrderActivity.class);
    	Button buttonText = (Button) findViewById(R.id.ViewOrderButton);
    	String message = buttonText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    	
    }
    
    public void placeOrder(View view){
    	System.out.println("placeOrder");
    }
    
}

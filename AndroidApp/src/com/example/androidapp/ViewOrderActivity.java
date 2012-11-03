package com.example.androidapp;

import core.Order;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ViewOrderActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        Intent intent = getIntent();
//        Order order = (Order) intent.getSerializableExtra("Order");
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        
        TextView textView = new TextView(this);
        textView.setTextSize(30);
        textView.setText(message);
        setContentView(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_order, menu);
        return true;
    }
}

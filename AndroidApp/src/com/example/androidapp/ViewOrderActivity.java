package com.example.androidapp;

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
        String orderString = intent.getStringExtra("Order");
        TextView textView = new TextView(this);
        textView.setTextSize(30);
        textView.setText(orderString);
        setContentView(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_order, menu);
        return true;
    }
}

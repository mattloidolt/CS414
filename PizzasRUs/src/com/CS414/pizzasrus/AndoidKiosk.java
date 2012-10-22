package com.CS414.pizzasrus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AndoidKiosk extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andoid_kiosk);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_andoid_kiosk, menu);
        return true;
    }
}

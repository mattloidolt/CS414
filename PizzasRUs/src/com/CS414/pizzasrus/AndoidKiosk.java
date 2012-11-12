package com.CS414.pizzasrus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class AndoidKiosk extends Activity {
	private DB database = new DB();
	
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
    
    public void clickHandler(View view) {
    	switch(view.getId()) {
    	case R.id.menu_select:
    		final String[] menuList = database.getMenus();
    		String str = "Number of menus: " + menuList.length;
    		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    		
    		if(menuList.length > 0) {
    			AlertDialog.Builder builder = new AlertDialog.Builder(this);
    			builder.setTitle("Which Menu?")
    			.setItems(menuList, new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog, int which) {
    					Toast.makeText(getApplicationContext(), "Chose: " + menuList[which], Toast.LENGTH_SHORT).show();
    					dialog.cancel();
    				}
    			});
    			AlertDialog alert = builder.create();
    			alert.show();
    		}
    		break;
    		
    		default:
    		break;
    	}
    }
}
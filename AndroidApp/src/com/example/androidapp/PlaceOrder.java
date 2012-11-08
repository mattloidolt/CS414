package com.example.androidapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.KioskCont;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PlaceOrder extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        ((TextView) findViewById(R.id.statusText)).setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_place_order, menu);
        return true;
    }
    
    private boolean validFields(){
    	String phone = getTextFieldString((EditText)findViewById(R.id.editText2));
    	String creditNumber = getTextFieldString((EditText)findViewById(R.id.editText5));
    	String creditExp = getTextFieldString((EditText)findViewById(R.id.editText6));
    	
    	if (phone.length() != 10)
    		return false;
    	if (creditNumber.length() != 16)
    		return false;
    	if (!isValidDate(creditExp))
    		return false;
    	return true;
    }
    
    private boolean isValidDate(String date) {
    	SimpleDateFormat dateFmt = new SimpleDateFormat("MM/yyyy");
    	Date testDate = null;
    	try {
    		testDate = dateFmt.parse(date);
    	} catch (ParseException e){
    		return false;
    	}
    	if (!dateFmt.format(testDate).equals(date)) {
    		return false;
    	}
    	return true;
    }
    
    private String getTextFieldString(EditText field){
    	return field.getText().toString();
    }
    
    private ArrayList<String> getElements(){
    	String name = getTextFieldString((EditText)findViewById(R.id.editText1));
    	String phone = getTextFieldString((EditText)findViewById(R.id.editText2));
    	String address = getTextFieldString((EditText)findViewById(R.id.editText3));
    	String creditName = getTextFieldString((EditText)findViewById(R.id.editText4));
    	String creditNumber = getTextFieldString((EditText)findViewById(R.id.editText5));
    	String creditExp = getTextFieldString((EditText)findViewById(R.id.editText6));
    	
    	ArrayList<String> list = new ArrayList<String>();
    	list.add(name);
    	list.add(phone);
    	list.add(address);
    	list.add(creditName);
    	list.add(creditNumber);
    	list.add(creditExp);
    	return list;
    }
    
    public void placeOrder(View view) {
    	if (validFields()){
    		KioskCont.saveOrder(this.getElements());
    	}
    }
}

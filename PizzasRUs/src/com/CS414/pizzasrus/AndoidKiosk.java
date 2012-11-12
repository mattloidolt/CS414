package com.CS414.pizzasrus;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.os.AsyncTask;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AndoidKiosk extends Activity {
	private DB database = new DB();
	private String storeText = "";
	
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
    		Button b = (Button)findViewById(R.id.menu_select);
    		b.setText("5");
    		new LongRunningGetIO().execute();
    		final ArrayList<String> menuList = new ArrayList<String>();

    		String str = "Number of menus: " + menuList.size();
    		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    		if(menuList.size() > 0) {
    			AlertDialog.Builder builder = new AlertDialog.Builder(this);
    			builder.setTitle("Which Menu?")
    			.setItems(menuList.toArray(new String[menuList.size()]), new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog, int which) {
    					Toast.makeText(getApplicationContext(), "Chose: " + menuList.get(which), Toast.LENGTH_SHORT).show();
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
    
    private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
		
		protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
	       InputStream in = entity.getContent();
	         StringBuffer out = new StringBuffer();
	         int n = 1;
	         while (n>0) {
	             byte[] b = new byte[4096];
	             n =  in.read(b);
	             if (n>0) out.append(new String(b, 0, n));
	         }
	         return out.toString();
	    }
		
		@Override
		protected String doInBackground(Void... params) {
			 HttpClient httpClient = new DefaultHttpClient();
			 HttpContext localContext = new BasicHttpContext();
             HttpGet httpGet = new HttpGet("http://10.0.2.2:8080/PizzaStoreWebApp/webresources/pizzastore.menus?");
             httpGet.setHeader("Accept", "application/json");
             httpGet.setHeader("Content-Type", "application/json");
             String text = null;
             try {
                   HttpResponse response = httpClient.execute(httpGet, localContext);
                   HttpEntity entity = response.getEntity();
                   text = getASCIIContentFromEntity(entity);
             } catch (Exception e) {
            	 return e.getLocalizedMessage();
             }
             return text;
		}	
		
		protected void onPostExecute(String results) {
			/*if (results!=null) {
				EditText et = (EditText)findViewById(R.id.my_edit);
				et.setText(results);
			}*/
			
    		Toast.makeText(getApplicationContext(), results, Toast.LENGTH_SHORT).show();
    		
			Button b = (Button)findViewById(R.id.menu_select);
			b.setText("10");
			b.setClickable(true);
		}
    }
}
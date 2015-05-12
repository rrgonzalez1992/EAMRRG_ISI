package com.busnow;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	private Spinner linea_selector;
	private Button submit_selectors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		linea_selector = (Spinner)findViewById(R.id.linea_selector);
		submit_selectors = (Button)findViewById(R.id.submit_selectors);
		List<String> values_linea_selector = new ArrayList<String>();
		values_linea_selector.add("Linea 1");
		values_linea_selector.add("Linea 2");
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values_linea_selector);
		adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		linea_selector.setAdapter(adp);
		submit_selectors.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int selectedLine = linea_selector.getSelectedItemPosition() +1;
				Intent intent =  new Intent(MainActivity.this, ServiceInvokeActivity.class);
				intent.putExtra("selectedLine", selectedLine);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

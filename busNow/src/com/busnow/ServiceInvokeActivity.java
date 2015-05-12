package com.busnow;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ServiceInvokeActivity extends Activity {

	private ListView listView;
	private int linea;
	private ArrayAdapter<String> arrayAdapter = null;
	private ArrayAdapter<String> arrayAdapterAtasco = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.services_layout);
		linea = getIntent().getIntExtra("selectedLine", 0);
		if(linea == 0) Toast.makeText(getApplicationContext(), "Fallo selectedLine", Toast.LENGTH_LONG).show();
		else{listView = (ListView) findViewById(R.id.ServicesResultList);
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		arrayAdapterAtasco = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		new InvocaServicioInformacion().execute();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.servicesmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.filtrarAtascados) {
			new InvocaServicioAtasco().execute();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public class InvocaServicioInformacion extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(
					"http://10.162.153.64:8080/ISI_REST/Servicios/obtenerInformacionLinea/"
							+ linea);
			httpGet.setHeader("content-type", "application/json");
			HttpResponse response = null;
			try {
				response = httpClient.execute(httpGet);
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String respStr = null;
			try {
				respStr = EntityUtils.toString(response.getEntity());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return respStr;
		}

		@Override
		protected void onPostExecute(String respStr) {
			if (!respStr.isEmpty()) {
				JSONArray jsonArray = null;
				try {
					jsonArray = new JSONArray(respStr);
					
					for (int i = 0; i < jsonArray.length(); i++) {
						String busID = null;
						double latitud = 0.0, longitud = 0.0;
						busID = jsonArray.getJSONObject(i).getString("busID");
						latitud = jsonArray.getJSONObject(i).getDouble(
								"latitud");
						longitud = jsonArray.getJSONObject(i).getDouble(
								"longitud");
						Autobus bus = new Autobus(busID, latitud, longitud);
						arrayAdapter.add(bus.toString());
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listView.setAdapter(arrayAdapter);
			}
		}
		
	}
	
	public class InvocaServicioAtasco extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(
					"http://10.162.153.64:8080/ISI_REST/Servicios/obtenerBusesAtascadosLinea/"
							+ linea);
			httpGet.setHeader("content-type", "application/json");
			HttpResponse response = null;
			try {
				response = httpClient.execute(httpGet);
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String respStr = null;
			try {
				respStr = EntityUtils.toString(response.getEntity());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return respStr;
		}

		@Override
		protected void onPostExecute(String respStr) {
			if (!respStr.isEmpty()) {
				JSONArray jsonArray = null;
				try {
					jsonArray = new JSONArray(respStr);
					
					for (int i = 0; i < jsonArray.length(); i++) {
						String busID = null;
						double latitud = 0.0, longitud = 0.0;
						busID = jsonArray.getJSONObject(i).getString("busID");
						latitud = jsonArray.getJSONObject(i).getDouble(
								"latitud");
						longitud = jsonArray.getJSONObject(i).getDouble(
								"longitud");
						Autobus bus = new Autobus(busID, latitud, longitud);
						arrayAdapterAtasco.add(bus.toString());
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listView.setAdapter(arrayAdapterAtasco);
			}
		}
		
	}
	
}

package com.example.ngo;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BrowseNGOs extends AppCompatActivity {
	ListView ngoListView;
	ArrayList<String> ngoList;
	DatabaseAdapter dataAdap;
	ArrayAdapter<String> listAdap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getSupportActionBar().hide();
		setContentView(R.layout.activity_browse_ngos);
		Drawable bgImage = getResources().getDrawable(R.drawable.bgg);
		bgImage.setAlpha(200);
		dataAdap = new DatabaseAdapter(this);
		ngoListView = (ListView) findViewById(R.id.ngoListView);
		ngoList = new ArrayList<String>();
		ArrayList<String> ngoNames = dataAdap.ngoNameList();
		listAdap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ngoNames);
		ngoListView.setAdapter(listAdap);
		ngoListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				String selectedItem = (String) ngoListView.getItemAtPosition(position);
				Intent ngoLay = new Intent(BrowseNGOs.this, NGOLay.class);
				ngoLay.putExtra("itemClicked", selectedItem);
				startActivity(ngoLay);

			}
		});


	}
	public void userProfile(View view){
	int x = 5;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse_ngos, menu);
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

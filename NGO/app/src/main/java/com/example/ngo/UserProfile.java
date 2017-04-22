package com.example.ngo;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class UserProfile extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getSupportActionBar().hide();
		setContentView(R.layout.activity_user_profile);
		Drawable bgImage = getResources().getDrawable(R.drawable.bgg);
		bgImage.setAlpha(200);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
		
		
	}
	
	public void BrowseNgo(View view){
		Intent browNgo = new Intent(this, BrowseNGOs.class);
		startActivity(browNgo);
		
	}

	public void NGOCommunity(View view){
		Intent NgoCommunity = new Intent(this, NGOCommunity.class);
		startActivity(NgoCommunity);

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

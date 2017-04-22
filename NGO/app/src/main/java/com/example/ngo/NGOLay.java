package com.example.ngo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class NGOLay extends Activity {
	String name;
	DatabaseAdapter dAdap;
	ImageView img;
	TextView pName;
	TextView pPhone;
	TextView pEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ngolay);

		Drawable bgImage = getResources().getDrawable(R.drawable.bgg);
		bgImage.setAlpha(200);
		Intent oldAct = getIntent();
		name = oldAct.getStringExtra("itemClicked");
		Context ctxt = getApplicationContext();
		dAdap = new DatabaseAdapter(ctxt);
		img = (ImageView)findViewById(R.id.profilePic);
		pName = (TextView)findViewById(R.id.profileName);
		pPhone = (TextView)findViewById(R.id.profileNum);
		pEmail = (TextView)findViewById(R.id.profileEmail);
		//img.setImageBitmap(dAdap.getProfileImage(name));
		img.setImageBitmap(BitmapFactory.decodeByteArray(dAdap.getProfileImage(name),0,dAdap.getProfileImage(name).length));
		pName.setText(dAdap.getProfileName(name));
		pPhone.setText(dAdap.getProfilePhone(name));;
		pEmail.setText(dAdap.getProfileEmail(name));;
		CharSequence toastMessage = "Hello " + name;
		int toastLength = Toast.LENGTH_SHORT;
		Toast incorrectInfo = Toast.makeText(ctxt, toastMessage, toastLength);
		incorrectInfo.show();

	}

	public void setProfile(){

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ngolay, menu);
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

package com.example.ngo;

import java.io.IOException;
import java.util.Locale;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;


public class SignUp extends AppCompatActivity {
	
	DatabaseAdapter dataAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Hides the actionBar
		this.getSupportActionBar().hide();
		setContentView(R.layout.activity_sign_up);
		Drawable bgImage = getResources().getDrawable(R.drawable.bgg);
		bgImage.setAlpha(200);
		dataAdapter = new DatabaseAdapter(this);
		EditText signUp = (EditText)findViewById(R.id.ConfirmPassWord);
		signUp.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				
				if (actionId == EditorInfo.IME_ACTION_DONE){
					View getView = getWindow().getDecorView().findViewById(android.R.id.content);
					try{
						registerUser(getView);
					}catch(IOException ex){
						ex.printStackTrace();
					}
				}
				return false;
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	
	public void registerUser(View view) throws IOException{
		
		EditText emailText = (EditText)findViewById(R.id.SignupEmail);
		EditText userText = (EditText)findViewById(R.id.SignupUserName);
		EditText passText = (EditText)findViewById(R.id.SignupPassWord);
		EditText confirmText = (EditText)findViewById(R.id.ConfirmPassWord);
		String emailStr = emailText.getText().toString().toLowerCase(Locale.getDefault());
		String userStr = userText.getText().toString().toLowerCase(Locale.getDefault());
		String passStr = passText.getText().toString();
		String confirmStr = confirmText.getText().toString();
		
		if(userStr.isEmpty()||emailStr.isEmpty()||passStr.isEmpty()){
			Context ctxt = getApplicationContext();
    		CharSequence toastMsg = "Please fill out Information correctly";
    		int toastLen = Toast.LENGTH_SHORT;
    		Toast incorrectInfo = Toast.makeText(ctxt, toastMsg, toastLen);
    		incorrectInfo.show();
    	}else if(dataAdapter.containsEmail(emailStr)){
    		Context ctxt = getApplicationContext();
    		CharSequence toastMsg = "Email Address already has an account";
    		int toastLen = Toast.LENGTH_SHORT;
    		Toast incorrectInfo = Toast.makeText(ctxt, toastMsg, toastLen);
    		incorrectInfo.show();
    	}else if(dataAdapter.isValidEmail(emailStr)){
    		Context ctxt = getApplicationContext();
    		CharSequence toastMsg = "Please Enter a valid email address";
    		int toastLen = Toast.LENGTH_SHORT;
    		Toast incorrectInfo = Toast.makeText(ctxt, toastMsg, toastLen);
    		incorrectInfo.show();
		}else if(dataAdapter.contains(userStr)){
			Context ctxt = getApplicationContext();
    		CharSequence toastMs = "Username is already taken, Please select a different username";
    		int toastLe = Toast.LENGTH_SHORT;
    		Toast incorrectInfo = Toast.makeText(ctxt, toastMs, toastLe);
    		incorrectInfo.show();
		}else if(passStr.equals(confirmStr)!=true){
			Context ctxt = getApplicationContext();
    		CharSequence toastMessage = "Passwords Do not match";
    		int toastLength = Toast.LENGTH_SHORT;
    		Toast incorrectInfo = Toast.makeText(ctxt, toastMessage, toastLength);
    		incorrectInfo.show();
		}else{
			dataAdapter.registerAccount(emailStr, userStr, passStr);
			Context ctxt = getApplicationContext();
    		CharSequence toastMessage = "Account has been Registered";
    		int toastLength = Toast.LENGTH_SHORT;
    		Toast registered = Toast.makeText(ctxt, toastMessage, toastLength);
    		registered.show();
			
		}
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

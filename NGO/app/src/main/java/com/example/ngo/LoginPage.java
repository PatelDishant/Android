package com.example.ngo;

import java.io.IOException;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
	
	DatabaseAdapter dbAdapter;

    
	/** OnCreate method does the following when LoginPage class is executed:
     * Initializes the DatabaseAdapter object*/
    
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Hides the actionBar
		this.getSupportActionBar().hide();
        setContentView(R.layout.activity_loginpage);

		Drawable bgImage = getResources().getDrawable(R.drawable.bgg);
		bgImage.setAlpha(200);

        dbAdapter = new DatabaseAdapter(this);
		EditText passWord = (EditText)findViewById(R.id.PassWord);
        passWord.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				if(actionId == EditorInfo.IME_ACTION_DONE){
					View newView = getWindow().getDecorView().findViewById(android.R.id.content);
					try {
						LoginToProfile(newView);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return false;
			}
		});
    }

	@Override
	protected void onPause() {
		super.onPause();
		EditText usrName = (EditText)findViewById(R.id.UserName);
		usrName.setText("");
		EditText passWord = (EditText)findViewById(R.id.PassWord);
		passWord.setText("");
	}

	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_page, menu);
        return true;
    }

    
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
    
    public void RegisterProfile(View view) throws IOException{
    	Intent registerIntent = new Intent(this, SignUp.class);
		startActivity(registerIntent);
    }
    
    
    public void LoginToProfile(View view) throws IOException{
    	
    	//Collect the information provided by the user
    	EditText UserName = (EditText)findViewById(R.id.UserName);
    	EditText PassWord = (EditText)findViewById(R.id.PassWord);
    	String user = UserName.getText().toString();
    	String pass = PassWord.getText().toString();
    	//Execute the method checkPassword which checks the username and password in the database. 
    	//checkPassword is a method in DatabaseAdapter class.
    	if(dbAdapter.checkPassword(user, pass)){
    		Intent intent = new Intent(this, UserProfile.class);
    		intent.putExtra("Username", user);
    		startActivity(intent);
    	//If the information provided by user is incorrect, output a short toast message 
    	}else{
    		Context ctxt = getApplicationContext();
    		CharSequence toastMessage = "Invalid Username or Password";
    		int toastLength = Toast.LENGTH_SHORT;
    		Toast incorrectInfo = Toast.makeText(ctxt, toastMessage, toastLength);
    		incorrectInfo.show();
    	}
    	
    }
}

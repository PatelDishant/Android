package b07.flightplanner;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Flights.SystemClass;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;

public class UploadClient extends ActionBarActivity {

	private SystemClass main;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_client);
		Intent intent = getIntent();
		main = (SystemClass) intent.getSerializableExtra("LoginID");
	}

	public void Upload(View view) throws IOException {
		EditText lastname = (EditText) findViewById(R.id.editLast);
		String LastName = lastname.getText().toString();

		EditText firstname = (EditText) findViewById(R.id.editName);
		String FirstName = firstname.getText().toString();

		EditText email = (EditText) findViewById(R.id.editEmail);
		String Email = email.getText().toString();

		EditText address = (EditText) findViewById(R.id.editAddress);
		String Address = address.getText().toString();

		EditText card = (EditText) findViewById(R.id.editCard);
		String Card = card.getText().toString();

		EditText expiry = (EditText) findViewById(R.id.editExpiry);
		String Expiry = expiry.getText().toString();

		String result = LastName + "," + FirstName + "," + Email + ","
				+ Address + "," + Card + "," + Expiry;
		
		String path = main.path + "/Client_copy.csv";
		BufferedWriter bw = new BufferedWriter(new FileWriter((path), true));
		bw.append(result);
		bw.close();
		finish();
		main.populate();
		Toast.makeText(getBaseContext(), FirstName +" Added to the database!", Toast.LENGTH_LONG).show();
	}

}

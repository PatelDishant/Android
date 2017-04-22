package b07.flightplanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Flights.SystemClass;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalActivity extends ActionBarActivity {

	private EditText firstname;
	private EditText lastname;
	private EditText email;
	private EditText address;
	private EditText card;
	private EditText expiry;
	private SystemClass main;
	private Intent intent;
	private String theClient = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Bobson,Bob,Bob@gmail.com,10 King Street,856956,2015-11-26
		setContentView(R.layout.activity_personal);

		intent = getIntent();
		main = (SystemClass) intent.getSerializableExtra("Main");
		theClient = main.getClient(main.username).toString();
		// lastname = (EditText) findViewById(R.id.lastname);
		// lastname.setText(main.getClient(main.username).getLastName());
		//
		// firstname = (EditText) findViewById(R.id.firstname);
		// firstname.setText(main.getClient(main.username).getFirstNames());
		//
		// email = (EditText) findViewById(R.id.email);
		// email.setText(main.getClient(main.username).getEmail());
		//
		// address = (EditText) findViewById(R.id.address);
		// address.setText(main.getClient(main.username).getAddress());
		//
		// card = (EditText) findViewById(R.id.cardnum);
		// card.setText(main.getClient(main.username).getCreditCardNumber());
		//
		// expiry = (EditText) findViewById(R.id.expiry);
		// expiry.setText(main.getClient(main.username).getExpiryDate());
	}

	public void insertClient(View view) throws IOException {
		lastname = (EditText) findViewById(R.id.lastname);
		String LastName = lastname.getText().toString();

		firstname = (EditText) findViewById(R.id.firstname);
		String FirstName = firstname.getText().toString();

		email = (EditText) findViewById(R.id.email);
		String Email = email.getText().toString();

		address = (EditText) findViewById(R.id.address);
		String Address = address.getText().toString();

		card = (EditText) findViewById(R.id.cardnum);
		String Card = card.getText().toString();

		expiry = (EditText) findViewById(R.id.expiry);
		String Expiry = expiry.getText().toString();

		String result = LastName + "," + FirstName + "," + Email + ","
				+ Address + "," + Card + "," + Expiry;

		String path = main.path + "/Client_copy.csv";
		BufferedReader br = new BufferedReader(new FileReader(path));
		BufferedWriter bw = new BufferedWriter(new FileWriter((path), true));

		String text;
		while ((text = br.readLine()) != null) {
			// parse the data
			if (text.length() > 0) {
				String[] elements = text.split(",");
				if (elements[2].equals(main.username)) {
					bw.write(result + "\n");
				}
			}
		}
		bw.close();
		br.close();
		finish();
		main.removeLine(path, theClient);
		main.populate();
		Toast.makeText(getBaseContext(), "Changes Saved!", Toast.LENGTH_LONG)
				.show();
		finish();
	}
}

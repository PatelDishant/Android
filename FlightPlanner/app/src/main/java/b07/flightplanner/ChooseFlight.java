package b07.flightplanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Flights.SystemClass;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChooseFlight extends ActionBarActivity {

	private Intent intent;
	private SystemClass main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_flight);
		intent = getIntent();
		main = (SystemClass) intent.getSerializableExtra("LoginID");
	}

	public void selectFlight(View view) throws IOException {

		EditText flight = (EditText) findViewById(R.id.FlightNumber);
		String flightnumber = flight.getText().toString();

		// read file to find old flight
		String path = main.path + "/Flight_copy.csv";
		BufferedReader br = new BufferedReader(new FileReader(path));
		BufferedWriter bw = new BufferedWriter(new FileWriter((path), true));

		String text;
		String oldFlight = "";
		while ((text = br.readLine()) != null) {
			// parse the data
			if (text.length() > 0) {
				String[] elements = text.split(",");
				if (elements[0].equals(flightnumber)) {
					oldFlight = text;
				}
			}
		}
		bw.close();
		br.close();

		if (oldFlight.equals("")) {
			Toast.makeText(getBaseContext(), flightnumber + " doesn't exist",
					Toast.LENGTH_LONG).show();
		} else {
			Intent editFlight = new Intent(this, EditFlight.class);
			editFlight.putExtra("Main", main);
			editFlight.putExtra("OldFlight", oldFlight);
			editFlight.putExtra("Number", flightnumber);
			startActivity(editFlight);
		}

	}
}

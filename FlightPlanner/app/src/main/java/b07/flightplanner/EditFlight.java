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

public class EditFlight extends ActionBarActivity {

	private Intent intent;
	private SystemClass main;
	private String oldFlight;
	private EditText flightNumber;
	private EditText arrival;
	private EditText departure;
	private EditText price;
	private EditText seats;
	private EditText start;
	private EditText dest;
	private EditText airline;
	private String number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_flight);

		intent = getIntent();
		main = (SystemClass) intent.getSerializableExtra("Main");
		oldFlight = (String) intent.getSerializableExtra("OldFlight");
		number = (String) intent.getSerializableExtra("Number");

		String[] flight = oldFlight.split(",");

		flightNumber = (EditText) findViewById(R.id.fnumber);
		flightNumber.setText(flight[0]);

		arrival = (EditText) findViewById(R.id.arrivaldate);
		arrival.setText(flight[2]);

		departure = (EditText) findViewById(R.id.depdate);
		departure.setText(flight[1]);

		price = (EditText) findViewById(R.id.cost);
		price.setText(flight[6]);

		seats = (EditText) findViewById(R.id.Numseat);
		seats.setText(flight[7]);

		start = (EditText) findViewById(R.id.originstop);
		start.setText(flight[4]);

		dest = (EditText) findViewById(R.id.dest);
		dest.setText(flight[5]);

		airline = (EditText) findViewById(R.id.airlines);
		airline.setText(flight[3]);

	}

	public void editflight(View view) throws IOException {
		// main.editFlights(oldFlight,newFlight);
		String path = main.path + "/Flight_copy.csv";
		BufferedReader br = new BufferedReader(new FileReader(path));
		BufferedWriter bw = new BufferedWriter(new FileWriter((path), true));
		String newFlight = flightNumber.getText().toString() + ","
				+ departure.getText().toString() + ","
				+ arrival.getText().toString() + ","
				+ airline.getText().toString() + ","
				+ start.getText().toString() + "," + dest.getText().toString()
				+ "," + price.getText().toString() + ","
				+ seats.getText().toString();
		
		String text;
		while ((text = br.readLine()) != null) {
			// parse the data
			if (text.length() > 0) {
				String[] elements = text.split(",");
				if (elements[0].equals(number)) {
					bw.append(newFlight + "\n");
				}
			}
		}
		bw.close();
		br.close();
		finish();
		main.removeLine(path, oldFlight);
		main.populate();
		Toast.makeText(getBaseContext(), "Changes Saved!", Toast.LENGTH_LONG)
				.show();
		finish();
	}

}

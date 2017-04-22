package b07.flightplanner;

import java.io.BufferedWriter;
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

public class UploadFlight extends ActionBarActivity {

	private SystemClass main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_flight);
		Intent intent = getIntent();
		main = (SystemClass) intent.getSerializableExtra("LoginID");
	}

	public void Upload(View view) throws IOException {
		EditText flightNumber = (EditText) findViewById(R.id.number);
		String Number = flightNumber.getText().toString();

		EditText arrival = (EditText) findViewById(R.id.arrival);
		String ArrivalDateTime = arrival.getText().toString();

		EditText departure = (EditText) findViewById(R.id.departure);
		String DepartureDateTime = departure.getText().toString();

		EditText price = (EditText) findViewById(R.id.price);
		String Price = price.getText().toString();

		EditText seats = (EditText) findViewById(R.id.seats);
		String
		NumSeats = seats.getText().toString();

		EditText start = (EditText) findViewById(R.id.start);
		String Origin = start.getText().toString();

		EditText finsih = (EditText) findViewById(R.id.finsih);
		String Destination = finsih.getText().toString();

		EditText airline = (EditText) findViewById(R.id.airline);
		String Airline = airline.getText().toString();

		String result = Number + "," + DepartureDateTime + ","
				+ ArrivalDateTime + "," + Airline + "," + Origin + ","
				+ Destination + "," + Price + "," + NumSeats;

		String path = main.path + "/Flight_copy.csv";
		BufferedWriter bw = new BufferedWriter(new FileWriter((path), true));
		bw.append(result + "\n");
		bw.close();
		finish();
		main.populate();
		Toast.makeText(getBaseContext(), "Flight " + Number + " added to the database!",
				Toast.LENGTH_LONG).show();
	}
}

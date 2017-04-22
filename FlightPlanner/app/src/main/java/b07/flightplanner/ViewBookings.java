package b07.flightplanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Flights.Driver;
import Flights.Flight;
import Flights.SystemClass;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewBookings extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_bookings);
		Intent intent = getIntent();
		// get the systemClass passed from intent
		SystemClass user = (SystemClass) intent.getSerializableExtra("Main");
		// get the path of the booked flights txt
		File path = this.getApplicationContext().getFilesDir();
		String directory = path.getAbsolutePath();

		String flights = "";
		try {
			flights = user.getBookings(directory + "/bookFlights.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		LinearLayout layout = (LinearLayout) findViewById(R.id.displayBooking);

		// set bg colour
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.parseColor("#424542"));
		TextView flight;
		if (flights.equals("")) {
			flight = new TextView(this);
			flight.setTextColor(Color.parseColor("#ffffff"));
			flight.setText("You have no bookings!");
			layout.addView(flight);
		} else {
			// for each flight dispay a textview on the activity
			for (String curFlight : flights.split("\n")) {
				
				flight = new TextView(getApplicationContext());
				flight.setText(curFlight.replace(", ", "\n"));
				// set set size, colour, and id
				flight.setTextSize(10);
				flight.setTextColor(Color.parseColor("#ffffff"));
				flight.setBackgroundColor(Color.parseColor("#424542"));

				// add each view to layout
				layout.addView(flight);

			}
		}
	}
}

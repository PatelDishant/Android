package b07.flightplanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SelectFlight extends ActionBarActivity {

	private Intent intent;
	private SystemClass main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_flight);

		intent = getIntent();
		main = (SystemClass) intent.getSerializableExtra("Main");
		final String flightNumber = (String) intent.getSerializableExtra("Number");
		String[] oldFlight = (String[]) intent
				.getSerializableExtra("OldFlight");
		// find the layout to put it under
		LinearLayout viewSelectFlights = (LinearLayout) findViewById(R.id.viewSelectFlights);
		int i = 0;

		// Set the background colour
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.parseColor("#424542"));

		// for each flight dispay a textview on the activity
		for (final String curFlight : oldFlight) {

			// create the new textview
			final TextView flight = new TextView(getApplicationContext());

			flight.setText(curFlight);

			// set set size, colour, and id
			flight.setTextSize(15);
			flight.setTextColor(Color.parseColor("#ffffff"));
			flight.setBackgroundColor(Color.parseColor("#424542"));
			flight.setId(i);
			i += 1;
			flight.setClickable(true);
			final Intent EditFlight = new Intent(this,EditFlight.class);
			// when each text is clicked do this
			flight.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// button was clicked!
					if (flight.isSelected() == true) {
						// make textView unselected and change colour
						flight.setSelected(false);
						flight.setClickable(true);
						flight.setTextColor(Color.parseColor("#fa8205"));
						
						EditFlight.putExtra("Main", main);
						EditFlight.putExtra("OldFlight", curFlight);
						EditFlight.putExtra("Number", flightNumber);
						startActivity(EditFlight);
						flight.setTextColor(Color.parseColor("#ffffff"));

					} else {
						// reset select and colour
						flight.setSelected(true);
						flight.setClickable(true);
						flight.setTextColor(Color.parseColor("#ffffff"));

					}
				}
			});
			// add each view to layout
			viewSelectFlights.addView(flight);

		}
	}
}

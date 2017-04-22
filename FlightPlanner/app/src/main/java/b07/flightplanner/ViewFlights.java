package b07.flightplanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import Flights.SystemClass;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewFlights extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_flights);
		// set bg colour
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.parseColor("#424542"));
		// get intent
		Intent intent = getIntent();
		// get the systemClass passed from intent
		final SystemClass user = (SystemClass) intent
				.getSerializableExtra("Main");
		// get the params
		String[] flight_array = (String[]) intent
				.getSerializableExtra("flight");
		// get which button was pressed
		final String searchType = (String) intent
				.getSerializableExtra("Search");

		String Filter = (String) intent.getSerializableExtra("filter");
		// set top menu
		TextView originflight = (TextView) findViewById(R.id.originflight);
		TextView destflight = (TextView) findViewById(R.id.destflight);
		originflight.setText(flight_array[0]);
		destflight.setText(flight_array[1]);

		// get the string rep of flights
		String flights = "";
		String spliter = "";
		if (searchType.equals("searchItinerary")) {
			if (Filter.equals("regular")) {
				flights = user.getItineraries(flight_array[0], flight_array[1],
						flight_array[2]);
				spliter = "\n\n";
			} else if (Filter.equals("time")) {
				flights = user.getItinerariesByTime(flight_array[0],
						flight_array[1], flight_array[2]);
				spliter = "\n\n";
			} else if (Filter.equals("cost")) {
				flights = user.getItinerariesByCost(flight_array[0],
						flight_array[1], flight_array[2]);
				spliter = "\n\n";
			}
		} else {
			flights = user.getFlights(flight_array[0], flight_array[1],
					flight_array[2]);
			spliter = "\n";
		}
		// find the layout to put it under
		LinearLayout main_layer = (LinearLayout) findViewById(R.id.display);
		int i = 0;

		// for each flight dispay a textview on the activity
		for (final String curFlight : flights.split(spliter)) {

			// create the new textview
			final TextView flight = new TextView(getApplicationContext());

			flight.setText(curFlight);

			// set set size, colour, and id
			flight.setTextSize(11);
			flight.setTextColor(Color.parseColor("#ffffff"));
			flight.setBackgroundColor(Color.parseColor("#424542"));
			flight.setId(i);
			i += 1;
			flight.setClickable(true);

			// get the path of the booked flights txt
			final String path = this.getApplicationContext().getFilesDir()
					.getAbsolutePath()
					+ "/bookFlights.txt";

			final File file = new File(path);

			// when each text is clicked do this
			flight.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					String[] data = curFlight.split("\n");
					String appendLine = "";
					if (searchType.equals("searchFlight")) {
						appendLine =  curFlight;
					} else if (searchType.equals("searchItinerary")) {
						appendLine = Arrays.toString(data).replace("[", "")
								.replace("]", "");
					}
					// button was clicked!
					if (flight.isSelected() == true) {
						// make textView unselected and change colour
						flight.setSelected(false);
						flight.setClickable(true);
						flight.setTextColor(Color.parseColor("#fa8205"));
						// book flight and write it to file

						try {

							FileWriter fw = new FileWriter(file, true);
							BufferedWriter writeFile = new BufferedWriter(fw);

							writeFile.append(user.username + "|" + appendLine
									+ "\r\n");
							writeFile.close();

						} catch (IOException e) {
							e.printStackTrace();
						}
						// find the booking on file and delete it
					} else {
						// reset select and colour
						flight.setSelected(true);
						flight.setClickable(true);
						flight.setTextColor(Color.parseColor("#ffffff"));
						// unbook flight
						user.removeLine(path, user.username + "|"
								+ appendLine);
						user.removeLine(path, "");
					}
				}
			});
			// add each view to layout
			main_layer.addView(flight);

		}

	}

}

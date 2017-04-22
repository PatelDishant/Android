package b07.flightplanner;

import Flights.SystemClass;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFlight extends ActionBarActivity {
	private String[] display;
	private Spinner CostTime;
	private Intent intent;
	private Intent searchIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_flight);
		searchIntent = new Intent(this, ViewFlights.class);
		CostTime = (Spinner) findViewById(R.id.filter);
		display = getResources().getStringArray(R.array.CostTime);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, display);
		CostTime.setAdapter(adapter);
		CostTime.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				int i = parent.getSelectedItemPosition();
				// Toast.makeText(getBaseContext(), display[i],
				// Toast.LENGTH_LONG).show();

				if (display[i].equals("Time")) {
					searchIntent.putExtra("filter", "time");
				} else if (display[i].equals("Cost")) {
					searchIntent.putExtra("filter", "cost");
				} else if (display[i].equals("Regular")) {
					searchIntent.putExtra("filter", "regular");
				} else {
					searchIntent.putExtra("filter", "");
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_flight, menu);
		return true;
	}

	public void sFlight(View view) {
		EditText origin = (EditText) findViewById(R.id.origin);
		String Origin = origin.getText().toString();
		EditText destination = (EditText) findViewById(R.id.destination);
		String Destination = destination.getText().toString();
		EditText date = (EditText) findViewById(R.id.date);
		String Date = date.getText().toString();
		String[] flight = { Origin, Destination, Date };
		intent = getIntent();
		if (Origin.equals("")) {
			Toast.makeText(getBaseContext(), "Invalid Origin",
					Toast.LENGTH_LONG).show();
		} else if (Destination.equals("")) {
			Toast.makeText(getBaseContext(), "Invalid Destination",
					Toast.LENGTH_LONG).show();
		} else if (Date.equals("")) {
			Toast.makeText(getBaseContext(), "Invalid Date", Toast.LENGTH_LONG)
					.show();
		} else {
			String searchType = (String) intent.getSerializableExtra("Search");
			SystemClass user = (SystemClass) intent
					.getSerializableExtra("Main");
			searchIntent.putExtra("Main", user);
			searchIntent.putExtra("flight", flight);
			searchIntent.putExtra("Search", searchType);
			startActivity(searchIntent);
		}
	}
}

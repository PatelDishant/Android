package b07.flightplanner;

import java.io.IOException;

import Flights.SystemClass;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DisplayActivity extends ActionBarActivity {

	private Intent intent;
	private SystemClass main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);

		// Get the intent that was passed to create our main
		intent = getIntent();
		main = (SystemClass) intent.getSerializableExtra("LoginID");

		// Set the background colour
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.parseColor("#424542"));

		// Get the String intent that defines a user as Client or Admin
		final String user = (String) intent.getSerializableExtra("User");

		// Display extra buttons if user is an Admin
		if (user.equals("Admin")) {
			Toast.makeText(getBaseContext(), "Welcome Admin", Toast.LENGTH_LONG)
					.show();

			// Find the buttons by its ID
			Button UploadClient = (Button) findViewById(R.id.AddClient);
			Button UploadFlight = (Button) findViewById(R.id.AddFlight);
			Button EditClient = (Button) findViewById(R.id.editclient);
			Button EditFlight = (Button) findViewById(R.id.editflight);
			Button ViewInfo = (Button) findViewById(R.id.viewClientInfo);
			Button ViewBooking = (Button) findViewById(R.id.viewClientBookings);
			Button uClient = (Button) findViewById(R.id.UploadClient);
			Button uFlight = (Button) findViewById(R.id.UploadFlight);

			// Set the buttons text and color
			setValues(UploadClient, "Add Clients");
			setValues(UploadFlight, "Add Flight");
			setValues(EditClient, "Edit Clients");
			setValues(EditFlight, "Edit Flight");
			setValues(ViewInfo, "View Client Information");
			setValues(ViewBooking, "View Client Bookings");
			setValues(uClient, "Upload Clients");
			setValues(uFlight, "Upload Flight");

			uClient.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					try {
						main.UploadClientInfo();
						Toast.makeText(getBaseContext(), "Upload Finished!",
								Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
						Toast.makeText(getBaseContext(), "Upload Failed!",
								Toast.LENGTH_LONG).show();
					}
				}
			});

			// Create a new Intent that goes to the PopulateClient class when
			// pressed and pass the Systemclass/main

			uFlight.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					try {
						main.UploadFlightInfo();
						Toast.makeText(getBaseContext(), "Upload Finished!",
								Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
						Toast.makeText(getBaseContext(), "Upload Failed!",
								Toast.LENGTH_LONG).show();
					}
				}
			});

			// Create a new Intent that goes to the UploadClient class when
			// pressed and pass the Systemclass/main
			final Intent AddClientintent = new Intent(this, UploadClient.class);

			UploadClient.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					AddClientintent.putExtra("LoginID", main);
					startActivity(AddClientintent);
				}
			});

			// Create a new Intent that goes to the UploadFlight class when
			// pressed and pass the Systemclass/main
			final Intent UploadFlightintent = new Intent(this,
					UploadFlight.class);

			UploadFlight.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					UploadFlightintent.putExtra("LoginID", main);
					startActivity(UploadFlightintent);
				}
			});
			// Create a new Intent that goes to the EditClient class when
			// pressed and pass the Systemclass/main
			final Intent EditClientintent = new Intent(this, ChooseClient.class);

			EditClient.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					EditClientintent.putExtra("LoginID", main);
					EditClientintent.putExtra("type", "EditClient");
					startActivity(EditClientintent);
				}
			});

			// Create a new Intent that goes to the EditFlight class when
			// pressed and pass the Systemclass/main
			final Intent EditFlightintent = new Intent(this, ChooseFlight.class);

			EditFlight.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					EditFlightintent.putExtra("LoginID", main);
					startActivity(EditFlightintent);
				}
			});

			// Create a new Intent that goes to the ChooseClient class when
			// pressed and pass the Systemclass/main
			final Intent ViewInfoIntent = new Intent(this, ChooseClient.class);

			ViewInfo.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					ViewInfoIntent.putExtra("LoginID", main);
					ViewInfoIntent.putExtra("type", "ViewInfo");
					startActivity(ViewInfoIntent);
				}
			});

			// Create a new Intent that goes to the ChooseClient class when
			// pressed and pass the Systemclass/main
			final Intent ViewBookingIntent = new Intent(this,
					ChooseClient.class);

			ViewBooking.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					ViewBookingIntent.putExtra("LoginID", main);
					ViewBookingIntent.putExtra("type", "ViewBooking");
					startActivity(ViewBookingIntent);
				}
			});

		} else {
			Toast.makeText(getBaseContext(), "Welcome Client",
					Toast.LENGTH_LONG).show();
		}
	}

	private void setValues(Button button, String text) {

		button.setBackgroundResource(R.drawable.button_shape);
		button.setText(text);
		button.setTextColor(Color.parseColor("#ffffff"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

	public void searchFlight(View view) {
		Intent searchIntent = new Intent(this, SearchFlight.class);
		String searchType = "searchFlight";
		searchIntent.putExtra("Search", searchType);
		searchIntent.putExtra("Main", main);
		startActivity(searchIntent);
	}

	public void viewInfo(View view) {
		Intent viewIntent = new Intent(this, PersonalActivity.class);
		viewIntent.putExtra("Main", main);
		startActivity(viewIntent);
	}

	public void viewBookings(View view) {
		Intent viewIntent = new Intent(this, ViewBookings.class);
		viewIntent.putExtra("Main", main);
		startActivity(viewIntent);
	}

	public void searchItinerary(View view) {
		Intent searchIntent = new Intent(this, SearchFlight.class);
		String searchType = "searchItinerary";
		searchIntent.putExtra("Search", searchType);
		searchIntent.putExtra("Main", main);
		startActivity(searchIntent);

	}
	
	public void Logout(View view) {
		Intent logoutIntent = new Intent(this, MainActivity.class);
		logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		finish();
		startActivity(logoutIntent);
	}

}

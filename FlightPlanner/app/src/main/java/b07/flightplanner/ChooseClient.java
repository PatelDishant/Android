package b07.flightplanner;

import Flights.SystemClass;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChooseClient extends ActionBarActivity {

	private Intent intent;
	private SystemClass main;
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_client);
		intent = getIntent();
		main = (SystemClass) intent.getSerializableExtra("LoginID");
		type = (String) intent.getSerializableExtra("type");

	}

	public void selectClient(View view) {

		EditText clientName = (EditText) findViewById(R.id.clientName);
		String email = clientName.getText().toString();
		if (main.getClient(email) == null) {
			Toast.makeText(getBaseContext(), email + " doesn't exist",
					Toast.LENGTH_LONG).show();
		} else {
			main.username = email;
			if (type.equals("EditClient")) {
				Intent viewIntent = new Intent(this, PersonalActivity.class);
				viewIntent.putExtra("Main", main);
				startActivity(viewIntent);
			} else if (type.equals("ViewInfo")) {
				Intent viewIntent = new Intent(this, PersonalActivity.class);
				viewIntent.putExtra("Main", main);
				startActivity(viewIntent);
			} else if (type.equals("ViewBooking")) {
				Intent viewIntent = new Intent(this, ViewBookings.class);
				viewIntent.putExtra("Main", main);
				startActivity(viewIntent);
			}
		}
	}
}

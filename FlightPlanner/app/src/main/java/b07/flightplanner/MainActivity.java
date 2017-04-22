package b07.flightplanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Flights.Client;
import Flights.SystemClass;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	// The Hash map to store login and passwords:
	// key: email Address value: (password, user status)
	private HashMap<String, List<String>> passwords = new HashMap<String, List<String>>();

	
	// Get the directory where files exist
	private String directory;

	// Overriding the what happens on onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Display this onCreate
		setContentView(R.layout.activity_main);

		// Get the location of the file
		File path = this.getApplicationContext().getFilesDir();
		// This is the absolute path where our pushed file exist
		directory = path.getAbsolutePath();

		try {
			uploadPass(directory + "/passwords.txt", passwords);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Setting background colour
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.parseColor("#fffaee"));

	}

	/**
	 * Given a path to a file and a HashMap, this function populates the
	 * HashMap: key: email Address value: (password, user status). Note each
	 * line in the file must be of the following format: email address,
	 * password, user status
	 * 
	 * @param path
	 *            Path where the file exist
	 * @throws IOException
	 *             This is thrown if the file does not
	 */
	private void uploadPass(String path, HashMap<String, List<String>> map)
			throws IOException {
		List<String> items;
		BufferedReader br = new BufferedReader(new FileReader(path));
		String text;

		while ((text = br.readLine()) != null) {
			// parse the data
			String[] elements = text.split(",");
			items = new ArrayList<String>();
			items.add(elements[1]);
			items.add(elements[2]);
			// place it in to the map
			map.put(elements[0], items);
		}
		br.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Validates user login details, if user is not registered it will prompt
	 * not valid login credentials
	 * 
	 * @param view
	 *            This is a Button
	 * @throws IOException
	 *             Throws this exception if file can not be read
	 */
	public void EnterLogin(View view) throws IOException {
		Intent intent = new Intent(this, DisplayActivity.class);
		EditText Username = (EditText) findViewById(R.id.UserName);
		String userName = Username.getText().toString();
		EditText Password = (EditText) findViewById(R.id.Pass);
		String password = Password.getText().toString();
		String[] login = { userName, password };

		SystemClass user = new SystemClass(userName, directory);
		// Validate user credentials
		if (checkValid(login[0], login[1], passwords)) {
			if (isAdmin(login[0], passwords)) {
				intent.putExtra("User", "Admin");
				user.populate();
			} else {
				// if (FileExists(directory + "/Client_copy.csv")) {
				intent.putExtra("User", "Client");
				user.populate();
				// }
			}
			intent.putExtra("LoginID", user);
			startActivity(intent);
		} else {
			// If user credentials does not exist prompt message
			Toast.makeText(getBaseContext(), "Invalid Username or Password",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * This validates user credentials credentials
	 * 
	 * @param user
	 *            User email
	 * @param pass
	 *            User password
	 * @return true if user exists and false if user does not
	 */
	private boolean checkValid(String user, String pass,
			HashMap<String, List<String>> map) {
		for (Entry<String, List<String>> name : map.entrySet()) {

			if (user.equals(name.getKey())
					&& pass.equals(name.getValue().get(0))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * returns true if given email is of Admin's, otherwise false
	 * 
	 * @param email
	 *            Email of the user
	 * @param map
	 *            HashMap of User credentials
	 * @return true if email belongs to Admin otherwise return false
	 */
	public boolean isAdmin(String email, HashMap<String, List<String>> map) {
		if (map.get(email).get(1).equals("Admin")) {
			return true;
		}
		return false;
	}

	protected void onPause() {
		super.onPause();
		finish();
	}

	public boolean FileExists(String path) {
		File file = new File(path);
		if (file.isFile()) {
			return true;
		} else {
			return false;
		}
	}

}

package b07.flightplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Thread counter = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent intent = new Intent("b07.flightplanner.MAINACTIVITY");
					startActivity(intent);
				}
			}
		};
		counter.start();
	}

	protected void onPause() {
		super.onPause();
		finish();
	}
}

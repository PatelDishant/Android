package dev.dishant.exekutivefades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.View;
import android.widget.EditText;
import android.graphics.Typeface;
import android.widget.*;
import android.content.Intent;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class loginScreen extends AppCompatActivity {

    EditText userName;
    EditText userPass;
    Button btnLogin;
    Typeface font;
    private LoginButton loginButton;
    private CallbackManager callbackManager = CallbackManager.Factory.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set content view
        setContentView(R.layout.activity_login_screen);
        // Set the objects
        userName = (EditText)findViewById(R.id.editTextUsername);
        userPass = (EditText)findViewById(R.id.editTextPassword);
        btnLogin  = (Button)findViewById(R.id.btnLogin);
        font =  Typeface.createFromAsset(getAssets(), "Pacifico.ttf");

        // Set the font for the widgets
        userName.setTypeface(font);
        userPass.setTypeface(font);
        btnLogin.setTypeface(font);

        // Facebook login button
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();

                String userId = loginResult.getAccessToken().getUserId();
                String accessToken = loginResult.getAccessToken().getToken();

                Toast.makeText(loginScreen.this, "Hello, " + userId , Toast.LENGTH_LONG).show();

                Intent mainActivity = new Intent(loginScreen.this, mainScreen.class);
                startActivity(mainActivity);


            }

            @Override
            public void onCancel() {
                //info.setText("Login attempt cancelled.");
                Toast.makeText(loginScreen.this, "Cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
                //info.setText("Login attempt failed.");
                Toast.makeText(loginScreen.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    protected void newUser(View v)
    {
        Intent newUserActivity = new Intent(this, newUserActivity.class);
        startActivity(newUserActivity);
    }

    protected void forgotPass(View v)
    {
        Intent forgotPassActivity = new Intent(this, forgotPassActivity.class);
        startActivity(forgotPassActivity);
    }

    protected void loginFunction (View v)
    {
        if (validateLoginInfo(userName.getText().toString(), userPass.getText().toString()))
        {
            Intent mainScreenActivity = new Intent(this, mainScreen.class);
            startActivity(mainScreenActivity);

        }
        else
        {
            Toast.makeText(this, "Incorrect Username or Password",
                    Toast.LENGTH_LONG).show();
        }


    }

    private boolean validateLoginInfo(String userName, String userPass)
    {
        boolean validLoginCred;
        if (userName.toLowerCase().equals("dee") && userPass.equals("pass"))
        {
            validLoginCred = true;
        }
        else
        {
            validLoginCred = false;
        }
        return validLoginCred;
    }
}

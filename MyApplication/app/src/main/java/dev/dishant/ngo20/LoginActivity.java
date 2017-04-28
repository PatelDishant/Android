package dev.dishant.ngo20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class LoginActivity extends AppCompatActivity {

    LoginButton loginBtn;
    TextView loginTextView;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = (LoginButton)findViewById(R.id.btnFBLoginID);
        loginTextView = (TextView) findViewById(R.id.textView);
        callbackManager = CallbackManager.Factory.create();
        loginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginTextView.setText("Login Successfull \n" + "Hello, " + loginResult.getAccessToken()
                .getUserId() + "\n" + loginResult.getAccessToken().getToken());

            }

            @Override
            public void onCancel() {
                loginTextView.setText("Login Cancelled");

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

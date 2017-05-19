package dev.dishant.exekutivefades;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class newUserActivity extends AppCompatActivity {

    Button btnSignup;
    EditText userName;
    EditText userEmail;
    EditText userPass;
    EditText confirmEmail;
    Typeface font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        btnSignup = (Button)findViewById(R.id.btnSignup);
        userName = (EditText) findViewById(R.id.editTextUsername);
        userEmail = (EditText) findViewById(R.id.editTextEmail);
        userPass = (EditText) findViewById(R.id.editTextPassword);
        confirmEmail = (EditText) findViewById(R.id.editTextConfirmEmail);

        font =  Typeface.createFromAsset(getAssets(), "Pacifico.ttf");

        // Set the font for the widgets
        userName.setTypeface(font);
        userPass.setTypeface(font);
        btnSignup.setTypeface(font);
        userEmail.setTypeface(font);
        confirmEmail.setTypeface(font);
    }

    protected void signupFunction(View v)
    {
        switch(validateUserInfo(userName.getText().toString(), userPass.getText().toString(), userEmail.getText().toString(), confirmEmail.getText().toString()))
        {
            case 1:
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(this, "Please enter a password with at least 6 characters", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(this, "Please enter an email address", Toast.LENGTH_LONG).show();
                break;
            case 4:
                Toast.makeText(this, "Please make sure the confirm email and email are matching", Toast.LENGTH_LONG).show();
                break;
            case 0:
                Intent loginActivity = new Intent(this, loginScreen.class);
                startActivity(loginActivity);
                break;

        }

    }

    private int validateUserInfo(String userName, String userPass, String email, String confirmEmail)
    {
        int invalidNum = 0;
        if (userName.trim().length() == 0)
        {
            invalidNum = 1;
        }
        if (userPass.length() < 6)
        {
            invalidNum = 2;
        }
        if (email.length() == 0)
        {
            invalidNum = 3;
        }
        else if(!email.trim().equals(confirmEmail.trim()))
        {
            invalidNum = 4;
        }
        return invalidNum;
    }


}

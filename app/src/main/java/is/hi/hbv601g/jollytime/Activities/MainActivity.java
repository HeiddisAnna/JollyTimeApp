package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import is.hi.hbv601g.jollytime.FirebaseServices.AuthenticationService;
import is.hi.hbv601g.jollytime.Models.Date;
import is.hi.hbv601g.jollytime.Models.User;
import is.hi.hbv601g.jollytime.Models.Event;
import is.hi.hbv601g.jollytime.Models.Notification;
//import is.hi.hbv601g.jollytime.R;


public class MainActivity extends AppCompatActivity {

    private String email;
    private String password;

    private Button mSignInButton;
    private Button mCreateAccountButton;
    private EditText mEmailInput;
    private EditText mPasswordInput;

    private AuthenticationService authenticationService;


    // PRUFUGÖGN!!!


    private ArrayList<User> mUsersBank = new ArrayList<User>();


    // PRUFUGÖGN!!!


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authenticationService = new AuthenticationService(this);

        mSignInButton = (Button) findViewById(R.id.signin_button);
        mCreateAccountButton = (Button) findViewById(R.id.createaccount_button);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailInput = (EditText) findViewById(R.id.email_input);
                mPasswordInput = (EditText) findViewById(R.id.password_input);

                email = mEmailInput.getText().toString();
                password = mPasswordInput.getText().toString();

                authenticationService.signIn(email, password);
            }
        });

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(i);
            }
        });
    }

    public void onAuthenticationSuccess() {
        Intent i = new Intent(MainActivity.this, CalendarActivity.class);
        startActivity(i);
    }

    public void onAuthenticationFail() {
        Toast.makeText(MainActivity.this, "AuthenticationService failed.",
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStart() {
        super.onStart();

        Boolean check = authenticationService.isUserLoggedIn();
        if (check) {
            Intent i = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(i);
        }


    }



}

package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import is.hi.hbv601g.jollytime.FirebaseServices.Authentication;
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

    private Authentication authenticationService;


    // PRUFUGÖGN!!!


    private ArrayList<User> mUsersBank = new ArrayList<User>();


    // PRUFUGÖGN!!!


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // PRUFUGÖGN!!!

        mUsersBank.add(new User("Marín", "password", "mim17@hi.is",
                new ArrayList<Event>(), new ArrayList<Date>(), new ArrayList<User>(), new ArrayList<Notification>()));
        mUsersBank.add(new User("Birta", "password", "bds8@hi.is",
                new ArrayList<Event>(), new ArrayList<Date>(), new ArrayList<User>(), new ArrayList<Notification>()));


        ArrayList<User> f0 = new ArrayList<User>();
        f0.add(mUsersBank.get(0));
        ArrayList<User> f1 = new ArrayList<User>();
        f1.add(mUsersBank.get(0));


        mUsersBank.get(0).addFriend(mUsersBank.get(1));
        mUsersBank.get(0).addEvent(new Event("Dans", "Í Sporthúsinu",
                new GregorianCalendar(2018, 3, 10, 12,30),
                new GregorianCalendar(2018, 3, 10, 14,0),
                mUsersBank.get(0)));
        mUsersBank.get(0).addEvent(new Event("Píanó", "Í Tónó",
                new GregorianCalendar(2018, 3, 12, 14,30),
                new GregorianCalendar(2018, 3, 12, 16,0),
                mUsersBank.get(0)));
        mUsersBank.get(1).addFriend(mUsersBank.get(0));
        mUsersBank.get(1).addEvent(new Event("Læra", "Heima",
                new GregorianCalendar(2018, 3, 11, 12,30),
                new GregorianCalendar(2018, 3, 11, 14,0),
                mUsersBank.get(1)));
        mUsersBank.get(1).addEvent(new Event("Partý", "Niðrí bæ",
                new GregorianCalendar(2018, 3, 12, 15,30),
                new GregorianCalendar(2018, 3, 12, 20,0),
                mUsersBank.get(1)));

        // PRUFUGÖGN!!!

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authenticationService = new Authentication();

        mSignInButton = (Button) findViewById(R.id.signin_button);
        mCreateAccountButton = (Button) findViewById(R.id.createaccount_button);
        mEmailInput = (EditText) findViewById(R.id.email_input);
        mPasswordInput = (EditText) findViewById(R.id.password_input);

        email = mEmailInput.getText().toString();
        password = mPasswordInput.getText().toString();

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean check = authenticationService.signIn(email, password);
                if (check) {
                    Intent i = new Intent(MainActivity.this, CalendarActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
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

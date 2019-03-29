package is.hi.hbv601g.jollytime.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.hi.hbv601g.jollytime.FirebaseServices.Authentication;
import is.hi.hbv601g.jollytime.Services.UserService;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String USERS = "is.hi.hbv601g.jollytime.users";


    private Authentication authenticationService;
    private UserService userService;


    private String name;
    private String email;
    private String password1;
    private String password2;

    private EditText mName;
    private EditText mEmail;
    private EditText mPassword1;
    private EditText mPassword2;

    private Button mCreateAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        authenticationService = new Authentication();

        mCreateAccountButton = (Button) findViewById(R.id.create_button);

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mName = (EditText) findViewById(R.id.name_input);
                mEmail = (EditText) findViewById(R.id.email_input);
                mPassword1 = (EditText) findViewById(R.id.password1_input);
                mPassword2 = (EditText) findViewById(R.id.password2_input);

                name = mName.getText().toString();
                email = mEmail.getText().toString();
                password1 = mPassword1.getText().toString();
                password2 = mPassword2.getText().toString();

                userService = new UserService(email, name, password1, password2);

                if (userService.isNameEmpty()) {

                    Toast.makeText(CreateAccountActivity.this, "Name cannot be empty",
                            Toast.LENGTH_SHORT).show();

                } else if (userService.isEmailEmpty()) {

                    Toast.makeText(CreateAccountActivity.this, "Email cannot be empty",
                            Toast.LENGTH_SHORT).show();

                } else if (userService.isEitherPasswordEmpty()) {

                    Toast.makeText(CreateAccountActivity.this, "Password cannot be empty",
                            Toast.LENGTH_SHORT).show();

                } else  if (!userService.doPasswordsMatch()) {

                    Toast.makeText(CreateAccountActivity.this, "Passwords do not match",
                            Toast.LENGTH_SHORT).show();

                } else {

                    Boolean check = authenticationService.createAccount(email, password1);

                    if (check) {
                        finish();
                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });
    }





}

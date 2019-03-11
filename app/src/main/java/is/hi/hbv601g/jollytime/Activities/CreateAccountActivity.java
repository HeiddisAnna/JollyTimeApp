package is.hi.hbv601g.jollytime.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import is.hi.hbv601g.jollytime.Models.User;
import is.hi.hbv601g.jollytime.Services.CreateAccount;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String USERS = "is.hi.hbv601g.jollytime.users";

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, CreateAccountActivity.class);
        //i.putExtra(USERS, users);
        return i;
    }


    private String name;
    private String email;
    private String password1;
    private String password2;

    private Button mCreateAccountButton;

    private CreateAccount createAccountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        mCreateAccountButton = (Button) findViewById(R.id.create_button);
        EditText mName = (EditText) findViewById(R.id.name_input);
        EditText mEmail = (EditText) findViewById(R.id.email_input);
        EditText mPassword1 = (EditText) findViewById(R.id.password1_input);
        EditText mPassword2 = (EditText) findViewById(R.id.password2_input);

        name = mName.getText().toString();
        email = mEmail.getText().toString();
        password1 = mPassword1.getText().toString();
        password2 = mPassword2.getText().toString();

        final Intent i = getIntent();
        final ArrayList<User> users = (ArrayList<User>) i.getSerializableExtra("USERS");


        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                createAccountService = new CreateAccount(name, email, password1, password2);
                if (createAccountService.checkIfEmailExists(users)) {
                    // email er til
                    Toast.makeText(CreateAccountActivity.this, R.string.email_exists,
                            Toast.LENGTH_LONG).show();
                }
                else if (!createAccountService.checkIfPasswordsMatch()) {
                    // password matcha ekki
                    Toast.makeText(CreateAccountActivity.this, R.string.passwords_not_matching,
                            Toast.LENGTH_LONG).show();
                } else {
                    // annars fer aftur til baka í sign in
                    i.putExtra("accountCreated", true);
                    setResult(RESULT_OK, i);
                    finish();
                }


            }

        });


    }
}

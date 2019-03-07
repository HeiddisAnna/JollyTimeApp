package is.hi.hbv601g.jollytime.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import is.hi.hbv601g.jollytime.R;


public class MainActivity extends AppCompatActivity {

    private String email;
    private String password;

    private Button mSignInButton;
    private EditText mEmailInput;
    private EditText mPasswordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSignInButton = (Button) findViewById(R.id.signin_button);
        mEmailInput = (EditText) findViewById(R.id.email_input);
        mPasswordInput = (EditText) findViewById(R.id.password_input);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmailInput.getText().toString();
                password = mPasswordInput.getText().toString();
            }
        });


    }


}

package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.hi.hbv601g.jollytime.FirebaseServices.FDB;
import is.hi.hbv601g.jollytime.Models.User;
import is.hi.hbv601g.jollytime.Services.AddFriend;

public class AddFriendActivity extends AppCompatActivity {

    private String name;
    private String email;

    private EditText mEmailInput;

    private Button mAddFriendButton;

    private AddFriend addFriendService;
    private FDB friendDatabaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        mAddFriendButton = (Button) findViewById(R.id.addFriend_button);
         friendDatabaseService = new FDB(this);

        mAddFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mEmailInput = (EditText) findViewById(R.id.friends_email_input);

                email = mEmailInput.getText().toString();
                friendDatabaseService.addFriend(email);

            }
        });
    }

    public void onAddingFriendSuccess() {
        finish();
    }

    public void onAddingFriendFail() {
        Toast.makeText(AddFriendActivity.this, "Adding this friend failed",
                Toast.LENGTH_SHORT).show();
    }
}

package is.hi.hbv601g.jollytime.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import is.hi.hbv601g.jollytime.Models.User;
import is.hi.hbv601g.jollytime.Services.AddFriend;

public class AddFriendActivity extends AppCompatActivity {

    private String name;
    private String email;

    private Button mAddFriendButton;

    private AddFriend addFriendService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);


        //mAddFriendButton = (Button) findViewById(R.id.addFriend_button);
    }
}

package is.hi.hbv601g.jollytime.FirebaseServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import is.hi.hbv601g.jollytime.Activities.CreateAccountActivity;
import is.hi.hbv601g.jollytime.Models.User;

import static com.firebase.ui.auth.AuthUI.TAG;

public class userDatabaseService {

    private DatabaseReference mDatabase;
    private AuthenticationService authenticationService;

    private CreateAccountActivity createAccountActivity;

    public userDatabaseService(AuthenticationService authenticationService) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.authenticationService = authenticationService;
    }


    public userDatabaseService(CreateAccountActivity createAccountActivity, AuthenticationService authenticationService) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.authenticationService = authenticationService;
        this.createAccountActivity = createAccountActivity;
    }

    public void saveNewUser(String email, String name) {
        User user = new User(email, name);

        String userID = authenticationService.getCurrentUserId();

        mDatabase.child(userID).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        createAccountActivity.onCreatingAccountSuccessfully();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("bla: ", e.getMessage());
                        createAccountActivity.onCreatingAccountFailure();
                    }
                });

    }

    /*
    public User findUser() {
        String userID = authenticationService.getCurrentUserId();

    }
    */


}
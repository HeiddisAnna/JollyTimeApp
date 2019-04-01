package is.hi.hbv601g.jollytime.FirebaseServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import is.hi.hbv601g.jollytime.Activities.CreateAccountActivity;
import is.hi.hbv601g.jollytime.Activities.MainActivity;


public class AuthenticationService {

    private FirebaseAuth mAuth;

    private userDatabaseService userDatabaseService;


    private MainActivity mainActivity;
    private CreateAccountActivity createAccountActivity;

    private String email;
    private String name;
    private String password;

    public AuthenticationService() {
        mAuth = FirebaseAuth.getInstance();
        userDatabaseService = new userDatabaseService(this);
    }

    public AuthenticationService(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mAuth = FirebaseAuth.getInstance();
    }

    public AuthenticationService(CreateAccountActivity createAccountActivity) {
        this.createAccountActivity = createAccountActivity;
        mAuth = FirebaseAuth.getInstance();
        userDatabaseService = new userDatabaseService(createAccountActivity, this);
    }

    public void addVars(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public String getCurrentUserId() {
        return getCurrentUser().getUid();
    }


    // Skilar true ef notandi er loggaður inn
    // Annars false
    public Boolean isUserLoggedIn() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            return false;
        } else {
            return true;
        }
    }

    public void createAccount() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(null, "createUserWithEmail:success");
                            // Create account in database
                            addUserToDB();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(null,"createUserWithEmail:failure");
                            createAccountActivity.onCreatingAccountFailure();
                        }

                    }
                });
    }

    public void addUserToDB() {
        userDatabaseService.saveNewUser(email, name);
    }


    public void signIn(String email, String password) {
        Log.i(null, "komin í signIn");
        mAuth.signInWithEmailAndPassword(email, password)

                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i(null, "signInWithEmail:success");
                            mainActivity.onAuthenticationSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i(null, "signInWithEmail:failure", task.getException());
                            mainActivity.onAuthenticationFail();
                        }
                    }
                });
    }


    public void signOut() {
        mAuth.signOut();
    }

}

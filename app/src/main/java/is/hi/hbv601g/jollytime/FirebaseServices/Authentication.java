package is.hi.hbv601g.jollytime.FirebaseServices;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import is.hi.hbv601g.jollytime.Activities.CalendarActivity;
import is.hi.hbv601g.jollytime.Activities.CreateAccountActivity;
import is.hi.hbv601g.jollytime.Activities.MainActivity;


public class Authentication {

    private FirebaseAuth mAuth;


    private MainActivity mainActivity;
    private CreateAccountActivity createAccountActivity;

    public Authentication() {
        mAuth = FirebaseAuth.getInstance();
    }

    public Authentication(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mAuth = FirebaseAuth.getInstance();
    }

    public Authentication (CreateAccountActivity createAccountActivity) {
        this.createAccountActivity = createAccountActivity;
        mAuth = FirebaseAuth.getInstance();
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

    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(null, "createUserWithEmail:success");
                            createAccountActivity.onCreatingAccountSuccessfully();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(null,"createUserWithEmail:failure");
                            createAccountActivity.onCreatingAccountFailure();
                        }

                    }
                });
    }


    public void signIn(String email, String password) {
        Log.i(null, "komin í signIn");
        mAuth.signInWithEmailAndPassword(email, password)

                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i(null, "Komin!!!");
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


    // skilar true ef notandi nær að skrá sig út
    // annars false
    public void signOut() {
        mAuth.signOut();
    }

}

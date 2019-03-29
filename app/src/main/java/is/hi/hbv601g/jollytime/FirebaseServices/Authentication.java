package is.hi.hbv601g.jollytime.FirebaseServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import is.hi.hbv601g.jollytime.Activities.MainActivity;

import static android.content.ContentValues.TAG;


public class Authentication {

    private FirebaseAuth mAuth;
    private Boolean check;

    public Authentication() {
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

    // Skilar true ef nýr aðgangur hefur verið búinn til
    // annars false
    public Boolean createAccount(String email, String password) {
        check = false;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            check = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            check = false;
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }

                    }
                });
        return check;
    }

    // skilar true ef innskráning virkar
    // annars false
    public Boolean signIn(String email, String password) {
        check = false;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            check = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            check = false;
                        }


                    }
                });
        return check;
    }

    public void signOut() {
        mAuth.signOut();
    }

}

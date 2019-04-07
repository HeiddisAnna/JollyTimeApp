package is.hi.hbv601g.jollytime.FirebaseServices;



import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import is.hi.hbv601g.jollytime.Activities.AddFriendActivity;
import is.hi.hbv601g.jollytime.Models.User;

public class FriendDatabaseService {

    private AuthenticationService authenticationService;
    private DatabaseReference mDatabase;
    private AddFriendActivity addFriendActivity;

    private String friendsEmail;
    private String friendId;

    public FriendDatabaseService(AddFriendActivity addFriendActivity) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.authenticationService = new AuthenticationService();
        this.addFriendActivity = addFriendActivity;
    }


    public void addFriend(String friendsEmail) {
        this.friendsEmail = friendsEmail;
        addFriendHelper();
    }



    public void addFriendHelper() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ArrayList<DataSnapshot> friendSnaps = new ArrayList<DataSnapshot>();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                User user;
                while (iterator.hasNext()) {
                    user = iterator.next().getValue(User.class);
                    if (user.getEmail().equals(friendsEmail)) {
                        addFriendIdToUser(user.getId());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                addFriendActivity.onAddingFriendFail();
            }
        });
    }

    public void addFriendIdToUser(String friendId) {
        this.friendId = friendId;
        String userId = authenticationService.getCurrentUserId();
        mDatabase.child(userId).child("friendIds").child(friendId).setValue(true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        addUserIdToFriend();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        addFriendActivity.onAddingFriendFail();
                    }
                });

    }

    public void addUserIdToFriend() {
        String userId = authenticationService.getCurrentUserId();
        mDatabase.child(friendId).child("friendIds").child(userId).setValue(true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        addFriendActivity.onAddingFriendSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        addFriendActivity.onAddingFriendFail();
                    }
                });

    }

}

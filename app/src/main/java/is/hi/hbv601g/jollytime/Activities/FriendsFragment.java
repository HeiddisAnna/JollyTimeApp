package is.hi.hbv601g.jollytime.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FriendsFragment extends Fragment {

    private RecyclerView myFriendsList;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_friends, container, false);

        myFriendsList = (RecyclerView) view.findViewById(R.id.friends_list);

        myFriendsList.setLayoutManager(new LinearLayoutManager(getContext()));

        Button addFriendButton = (Button) view.findViewById(R.id.addefriend_button);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddFriendActivity.class);
                startActivity(intent);
            }

        });

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();


    }
}

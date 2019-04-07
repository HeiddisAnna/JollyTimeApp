package is.hi.hbv601g.jollytime.Activities;


import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import is.hi.hbv601g.jollytime.FirebaseServices.AuthenticationService;

import java.util.ArrayList;

import is.hi.hbv601g.jollytime.Models.User;

public class CalendarActivity extends AppCompatActivity {

    private Button mSignOutButton;
    private AuthenticationService authenticationService;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private ArrayList<User> user;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        authenticationService = new AuthenticationService();

        // Navigation
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView_activity);

        //DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(navigationView);


        //mSignOutButton = (Button) findViewById(R.id.menu_logout);

        //final Intent i = getIntent();
        //user = (ArrayList<User>) i.getSerializableExtra("USER");


        /*mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticationService.signOut();
                finish();
            }
        });*/
    }


    public void selectItemDrawer(MenuItem item) {
        Fragment fragment = null;

        Class fragmentClass = null;

        switch (item.getItemId()) {
            case R.id.menu_calendar:
                fragmentClass = CalendarFragment.class;
                break;
            case R.id.menu_profile:
                fragmentClass = UserProfileFragment.class;
                break;
            case R.id.menu_friends:
                fragmentClass = FriendsFragment.class;
                break;
            case R.id.menu_messages:
                fragmentClass = MessagesFragment.class;
                break;
            case R.id.menu_bookADate:
                fragmentClass = BookADateFragment.class;
                break;
            case R.id.menu_settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.menu_logout:
                authenticationService.signOut();
                finish();
                break;
            default:
                fragmentClass = CalendarFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawerLayout.closeDrawers();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });
    }
}

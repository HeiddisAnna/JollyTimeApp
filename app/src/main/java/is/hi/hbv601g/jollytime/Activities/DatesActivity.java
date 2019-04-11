package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;
import is.hi.hbv601g.jollytime.Models.Event;
import is.hi.hbv601g.jollytime.Services.CreateEventService;

public class DatesActivity extends AppCompatActivity {

    private Button goToCalendar;
    private TextView datesList1;
    private TextView datesList2;
    private TextView datesList3;
    private TextView datesList4;
    private TextView datesList5;
    private TextView datesList6;
    private TextView datesList7;
    private TextView datesList8;
    private TextView datesList9;
    private TextView datesList0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);


        goToCalendar = (Button) findViewById(R.id.goToCalendar);

        datesList0 = (TextView) findViewById(R.id.datesList0);
        String startText0 = getIntent().getExtras().getString("startKey0");
        String endText0 = getIntent().getExtras().getString("endKey0");
        String use0= "";
        if(startText0 != null){
             use0 = "Available date: Start at " + startText0 + " and ends at " + endText0;
        }

        datesList0.setText(use0);

        datesList1 = (TextView) findViewById(R.id.datesList1);
        String startText1 = getIntent().getExtras().getString("startKey1");
        String endText1 = getIntent().getExtras().getString("endKey1");
        String use1= "";
        if(startText1 != null){
            use1 = "Available date: Start at " + startText1 + " and ends at " + endText1;
        }
        datesList1.setText(use1);

        datesList2 = (TextView) findViewById(R.id.datesList2);
        String startText2 = getIntent().getExtras().getString("startKey2");
        String endText2 = getIntent().getExtras().getString("endKey2");
        String use2= "";
        if(startText2 != null){
            use2 = "Available date: Start at " + startText2 + " and ends at " + endText2;
        }

        datesList2.setText(use2);

        datesList3 = (TextView) findViewById(R.id.datesList3);
        String startText3 = getIntent().getExtras().getString("startKey3");
        String endText3 = getIntent().getExtras().getString("endKey3");
        String use3= "";
        if(startText3 != null){
            use3 = "Available date: Start at " + startText3 + " and ends at " + endText3;
        }
        datesList3.setText(use3);

        datesList4 = (TextView) findViewById(R.id.datesList4);
        String startText4 = getIntent().getExtras().getString("startKey4");
        String endText4 = getIntent().getExtras().getString("endKey4");
        String use4= "";
        if(startText4 != null){
            use4 = "Available date: Start at " + startText4 + " and ends at " + endText4;
        }
        datesList4.setText(use4);

        datesList5 = (TextView) findViewById(R.id.datesList5);
        String startText5 = getIntent().getExtras().getString("startKey5");
        String endText5 = getIntent().getExtras().getString("endKey5");
        String use5= "";
        if(startText5 != null){
            use5 = "Available date: Start at " + startText5 + " and ends at " + endText5;
        }
        datesList1.setText(use5);

        datesList6 = (TextView) findViewById(R.id.datesList6);
        String startText6 = getIntent().getExtras().getString("startKey6");
        String endText6 = getIntent().getExtras().getString("endKey6");
        String use6= "";
        if(startText6 != null){
            use6 = "Available date: Start at " + startText6 + " and ends at " + endText6;
        }
        datesList6.setText(use6);

        datesList7 = (TextView) findViewById(R.id.datesList7);
        String startText7 = getIntent().getExtras().getString("startKey7");
        String endText7 = getIntent().getExtras().getString("endKey7");
        String use7= "";
        if(startText7 != null){
            use7 = "Available date: Start at " + startText7 + " and ends at " + endText7;
        }
        datesList7.setText(use7);

        datesList8 = (TextView) findViewById(R.id.datesList8);
        String startText8 = getIntent().getExtras().getString("startKey8");
        String endText8 = getIntent().getExtras().getString("endKey8");
        String use8= "";
        if(startText8 != null){
            use8 = "Available date: Start at " + startText8 + " and ends at " + endText8;
        }
        datesList8.setText(use8);

        datesList9 = (TextView) findViewById(R.id.datesList9);
        String startText9 = getIntent().getExtras().getString("startKey9");
        String endText9 = getIntent().getExtras().getString("endKey9");
        String use9= "";
        if(startText9 != null){
            use9 = "Available date: Start at " + startText9 + " and ends at " + endText9;
        }
        datesList9.setText(use9);




        goToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DatesActivity.this, CalendarActivity.class);
                startActivity(intent);

            }
        });
    }

}

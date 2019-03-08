package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        TextView dateTextView = findViewById(R.id.dateTextView);
        Button backToCalendarButton = findViewById(R.id.backToCalendarButton);

        String date = getIntent().getStringExtra("date");
        if(date!= null) {
            dateTextView.setText(date);
        }

        backToCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DayActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}

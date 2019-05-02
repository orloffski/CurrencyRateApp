package by.madcat.currencyrateapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import by.madcat.currencyrateapp.R;

public class PreferencesActivity extends AppCompatActivity {

    private LinearLayout datesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        datesLayout = findViewById(R.id.datesLayout);
        datesLayout.setVisibility(View.GONE);

    }

}

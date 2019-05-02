package by.madcat.currencyrateapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.AppUtilities;
import by.madcat.currencyrateapp.common.Currency;
import by.madcat.currencyrateapp.recyclerviews.MainRecyclerViewAdapter;
import by.madcat.currencyrateapp.viewmodel.CurrencyViewModel;

public class MainActivity extends AppCompatActivity {

    private MainRecyclerViewAdapter adapter;

    private TextView lastDate;
    private TextView prevDate;
    private LinearLayout datesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datesLayout = findViewById(R.id.datesLayout);
        lastDate = findViewById(R.id.lastDateTextView);
        prevDate = findViewById(R.id.prevDateTextView);

        datesLayout.setVisibility(View.GONE);

        adapter = new MainRecyclerViewAdapter(getApplication());

        CurrencyViewModel currenciesViewModel = ViewModelProviders.of(this).get(CurrencyViewModel.class);
        currenciesViewModel.getCurrenciesData().observe(this, currencies -> {
            Collections.sort(currencies);
            adapter.setData(currencies);

            if(datesLayout.getVisibility() != View.VISIBLE && currencies != null && currencies.size() > 0) {
                setHeaderDates(currencies.get(0));
                datesLayout.setVisibility(View.VISIBLE);
            }
        });

        currenciesViewModel.getMessageData().observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        RecyclerView recyclerView = findViewById(R.id.currencies_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem settings = menu.findItem(R.id.settings);

        if (!AppUtilities.isNetworkAvailableAndConnected(getApplication())) {
            settings.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    private void setHeaderDates(Currency currency){
        lastDate.setText(currency.getLastDate());
        prevDate.setText(currency.getPrevDate());
    }
}

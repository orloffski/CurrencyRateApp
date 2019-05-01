package by.madcat.currencyrateapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import by.madcat.currencyrateapp.R;
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

    private void setHeaderDates(Currency currency){

    }
}

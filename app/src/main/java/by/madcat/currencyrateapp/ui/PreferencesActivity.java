package by.madcat.currencyrateapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.List;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.Currency;
import by.madcat.currencyrateapp.recyclerviews.PrefsRecyclerViewAdapter;
import by.madcat.currencyrateapp.recyclerviews.SimpleItemTouchHelperCallback;
import by.madcat.currencyrateapp.viewmodel.CurrencyViewModel;

public class PreferencesActivity extends AppCompatActivity {

    PrefsRecyclerViewAdapter adapter;
    private LinearLayout datesLayout;

    SharedPreferences mSettings;
    Gson gson;
    List<Currency> currencies;

    public static final String APP_PREFERENCES = "currency_preferences";
    public static final String APP_PREFS_SAVED = "prefs_saved";
    public static final String APP_CURRENCY_PREFS = "currency_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        datesLayout = findViewById(R.id.datesLayout);
        datesLayout.setVisibility(View.GONE);

        CurrencyViewModel currenciesViewModel = ViewModelProviders.of(this).get(CurrencyViewModel.class);
        currencies = currenciesViewModel.getCurrenciesData().getValue();

        initRecyclerView();

        mSettings = getSharedPreferences(APP_PREFERENCES, getApplication().MODE_PRIVATE);

    }

    private void initRecyclerView(){
        adapter = new PrefsRecyclerViewAdapter(getApplication());
        adapter.setData(currencies);
        RecyclerView recyclerView = findViewById(R.id.prefs_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void loadSettings(){

    }

    private void saveSettings(){

    }
}

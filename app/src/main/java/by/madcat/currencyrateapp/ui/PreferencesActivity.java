package by.madcat.currencyrateapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
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

        mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        if(mSettings.getBoolean(APP_PREFS_SAVED, false))
            initSavedSettings();

        initRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.prefs_menu, menu);
        MenuItem save = menu.findItem(R.id.save_settings);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_settings:
                saveSettings();

                return true;
        }
        return false;
    }

    private void initSavedSettings(){
        List<Currency> settingsCurrencies = loadSettings();

        for(Currency currency : currencies)
            for(Currency savedCurr : settingsCurrencies)
                if(currency.getCurrencyCode().equalsIgnoreCase(savedCurr.getCurrencyCode())){
                    currency.setPosition(savedCurr.getPosition());
                    currency.setInclude(savedCurr.isInclude());
                }

        Collections.sort(currencies);
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

    private List<Currency> loadSettings(){
        Gson gson = new Gson();
        String jsonText = mSettings.getString(APP_CURRENCY_PREFS, null);
        Type type = new TypeToken<List<Currency>>() {}.getType();
        return gson.fromJson(jsonText, type);
    }

    private void saveSettings(){
        gson = new Gson();
        String jsonText = gson.toJson(adapter.getModifyList());
        SharedPreferences.Editor prefsEditor = mSettings.edit();
        prefsEditor.putBoolean(APP_PREFS_SAVED, true);
        prefsEditor.putString(APP_CURRENCY_PREFS, jsonText);
        prefsEditor.apply();
    }
}

package by.madcat.currencyrateapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.datarepository.Currency;
import by.madcat.currencyrateapp.viewmodel.CurrencyViewModel;

public class MainActivity extends AppCompatActivity {

    private CurrencyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel.class);
        viewModel.getData().observe(this, currencies -> {
            for (Currency currency:currencies) {
                Log.d("test", currency.toString());
            }
        });
    }
}

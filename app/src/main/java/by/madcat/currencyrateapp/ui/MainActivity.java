package by.madcat.currencyrateapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.Currency;
import by.madcat.currencyrateapp.viewmodel.CurrencyViewModel;
import by.madcat.currencyrateapp.viewmodel.MessagesViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CurrencyViewModel currenciesViewModel = ViewModelProviders.of(this).get(CurrencyViewModel.class);
        currenciesViewModel.getCurrenciesData().observe(this, currencies -> {
            for (Currency currency:currencies) {
                Log.d("test", currency.toString());
            }
        });

        MessagesViewModel messagesViewModel = ViewModelProviders.of(this).get(MessagesViewModel.class);
        messagesViewModel.getMessageData().observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
    }
}

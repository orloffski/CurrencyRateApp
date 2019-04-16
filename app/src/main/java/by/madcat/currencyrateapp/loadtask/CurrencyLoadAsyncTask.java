package by.madcat.currencyrateapp.loadtask;

import android.os.AsyncTask;
import android.util.Log;

import by.madcat.currencyrateapp.datarepository.CurrenciesRepository;
import by.madcat.currencyrateapp.datarepository.Currency;
import by.madcat.currencyrateapp.datarepository.CurrencyAppConstants;

public class CurrencyLoadAsyncTask extends AsyncTask<Void, Void, Void> {

    private String url_path;
    private CurrenciesRepository repository;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        url_path = CurrencyAppConstants.URL_PATH;
        repository = CurrenciesRepository.getInstance();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for(int i = 1; i <= 10; i++){
            Currency currency = new Currency(i, true, "Code; " + i);
            repository.addCurrency(currency);

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Log.d("test", e.getMessage());
                e.printStackTrace();
            }
        }

        return null;
    }
}

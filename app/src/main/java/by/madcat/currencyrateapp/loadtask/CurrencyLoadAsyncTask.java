package by.madcat.currencyrateapp.loadtask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import by.madcat.currencyrateapp.common.AppUtilities;
import by.madcat.currencyrateapp.repositories.CurrenciesRepository;
import by.madcat.currencyrateapp.common.Currency;
import by.madcat.currencyrateapp.common.CurrencyAppConstants;
import by.madcat.currencyrateapp.repositories.MessagesRepository;

public class CurrencyLoadAsyncTask extends AsyncTask<Void, Void, Void> {

    @SuppressLint("StaticFieldLeak")
    private Context context;

    private String url_path;
    private CurrenciesRepository currenciesRepository;

    public CurrencyLoadAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        url_path = CurrencyAppConstants.URL_PATH;
        currenciesRepository = CurrenciesRepository.getInstance();
        MessagesRepository messagesRepository = MessagesRepository.getInstance();

        if(!AppUtilities.isNetworkAvailableAndConnected(context)) {
            messagesRepository.updateMessage("Internet not connected");
            currenciesRepository.setDataLoaded(false);

            this.cancel(false);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for(int i = 1; i <= 10; i++){
            if(isCancelled())
                return null;

            Currency currency = new Currency(i, true, "Code; " + i);
            currenciesRepository.addCurrency(currency);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Log.d("test", e.getMessage());
                e.printStackTrace();
            }
        }

        currenciesRepository.setDataLoaded(true);

        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        context = null;
    }
}

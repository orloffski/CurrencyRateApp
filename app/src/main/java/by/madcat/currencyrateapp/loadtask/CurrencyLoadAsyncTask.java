package by.madcat.currencyrateapp.loadtask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import by.madcat.currencyrateapp.common.AppUtilities;
import by.madcat.currencyrateapp.common.Currency;
import by.madcat.currencyrateapp.common.GetCurrencyData;
import by.madcat.currencyrateapp.repositories.CurrenciesRepository;
import by.madcat.currencyrateapp.repositories.MessagesRepository;

public class CurrencyLoadAsyncTask extends AsyncTask<Void, Void, Void> {

    @SuppressLint("StaticFieldLeak")
    private Context context;

    private static CurrencyLoadAsyncTask instance;

    private List<Currency> currencyLast;
    private List<Currency> currencyPrev;

    public static Void runCurrencyLoadAsyncTask(Context context){
        if(instance == null) {
            instance = new CurrencyLoadAsyncTask(context);
            instance.execute();
        }

        return null;
    }

    private CurrencyLoadAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(!AppUtilities.isNetworkAvailableAndConnected(context)) {
            MessagesRepository.getInstance().updateMessage("Internet not connected");
            CurrenciesRepository.getInstance().setDataLoaded(false);

            this.cancel(false);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if(isCancelled())
            return null;

        try {
            loadCurrencies();
        } catch (IOException | XmlPullParserException e) {
            MessagesRepository.getInstance().updateMessage(e.getMessage());
        }

        combineCurrencies();

        CurrenciesRepository.getInstance().setDataLoaded(true);

        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        context = null;
    }

    private void loadCurrencies() throws IOException, XmlPullParserException {
        int i = 0;
        currencyLast = null;
        currencyPrev = null;

        while(currencyLast == null){
            currencyLast = GetCurrencyData.getData(AppUtilities.getDateFromInterval(i));
            i--;
        }

        while(currencyPrev == null){
            currencyPrev = GetCurrencyData.getData(AppUtilities.getDateFromInterval(i));
            i--;
        }
    }

    private void combineCurrencies(){
        for (Currency currency : currencyLast) {
            for(Currency currencyTmp : currencyPrev){
                if(currency.getCurrencyCode().equalsIgnoreCase(currencyTmp.getCurrencyCode())){
                    currency.setCurrencyPrevRate(currencyTmp.getCurrencyLastRate());
                    currency.setPrevDate(currencyTmp.getLastDate());
                }
            }

            CurrenciesRepository.getInstance().addCurrency(currency);
        }

        currencyPrev.clear();
    }
}

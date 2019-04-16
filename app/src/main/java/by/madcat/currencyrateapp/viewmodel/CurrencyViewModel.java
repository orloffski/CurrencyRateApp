package by.madcat.currencyrateapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import by.madcat.currencyrateapp.repositories.CurrenciesRepository;
import by.madcat.currencyrateapp.common.Currency;
import by.madcat.currencyrateapp.loadtask.CurrencyLoadAsyncTask;

public class CurrencyViewModel extends AndroidViewModel {

    private LiveData<List<Currency>> currencyLiveData;
    private CurrenciesRepository currenciesRepository;
    private boolean dataLoaded;

    public CurrencyViewModel(@NonNull Application application) {
        super(application);

        currenciesRepository = CurrenciesRepository.getInstance();
        currencyLiveData = currenciesRepository.getCurrencyData();
        currenciesRepository.setDataLoaded(false);
    }

    public LiveData<List<Currency>> getCurrenciesData(){
        if(!currenciesRepository.isDataLoaded())
            loadData();

        return currencyLiveData;
    }

    private void loadData(){
        CurrencyLoadAsyncTask t = new CurrencyLoadAsyncTask(getApplication());
        t.execute();
    }
}
